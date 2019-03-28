package com.aladen.common.config;

import com.aladen.common.util.IPUtil;
import com.aladen.entity.sys.SysTaskConfig;
import com.aladen.service.sys.ISysTaskConfigService;
import com.aladen.task.base.BaseJob;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.List;

/**
 * @Title: TaskConfig
 * @Description:
 * @Author xu
 * @Date 2019/3/27 14:29
 * @Version V1.0
 * @Copyright 2019 All Rights Reserved
 */
@Configuration
public class TaskConfig {
    private Logger logger = LoggerFactory.getLogger(TaskConfig.class);

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private ISysTaskConfigService taskConfigService;

    @Value("${spring.application.name}")
    private String serviceName;

    @Bean
    Scheduler scheduler() {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        List<String> localIps = IPUtil.getAllIPs();
        if (localIps == null || localIps.size() == 0) {
            logger.info("获取本地IP失败，不启动定时任务");
            return null;
        }
        // 查询有效的定时任务
        QueryWrapper<SysTaskConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status","1").eq("app_name",serviceName);
        List<SysTaskConfig> tasks = taskConfigService.list(queryWrapper);
        if (tasks != null && tasks.size() > 0) {
            for (SysTaskConfig taskConfig : tasks) {
                // 通过IP过滤
                if (StringUtils.isNotBlank(taskConfig.getIps())) {
                    boolean ipCheck = false;
                    if (localIps.contains(taskConfig.getIps())) {
                        ipCheck = true;
                    }
                    if (ipCheck) {
                        logger.info("通过IP校验，启动定时任务 -> {}",taskConfig.getName());
                    } else {
                        logger.info("未通过IP校验，跳过定时任务 -> {}",taskConfig.getName());
                        continue;
                    }
                    JobDetail jobDetail = JobBuilder.newJob(BaseJob.class).build();
                    CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(taskConfig.getTimeCron());
                    CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(taskConfig.getMethod(),taskConfig.getBeanRef()).withSchedule(cronScheduleBuilder).build();
                    try {
                        scheduler.scheduleJob(jobDetail,trigger);
                        logger.info("定时任务初始化完成; -> {}",taskConfig.getName());
                    } catch (SchedulerException e) {
                        logger.error("定时任务初始化失败");
                    }
                } else {
                    logger.info("未做IP限制;跳过定时任务->{}",taskConfig.getName());
                    continue;
                }

            }
        }
        return scheduler;
    }

}
