package com.aladen.task.job;

import com.aladen.entity.sys.SysTaskConfig;
import com.aladen.service.sys.ISysTaskConfigService;
import com.aladen.task.base.JobRunnable;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title: JobTask
 * @Description:
 * @Author xu
 * @Date 2019/3/27 15:31
 * @Version V1.0
 * @Copyright 2019 All Rights Reserved
 */
@Component("jobTask")
public class JobTask {
    private Logger logger = LoggerFactory.getLogger(JobTask.class);

    @Autowired
    private ISysTaskConfigService taskConfigService;

    private ExecutorService executorService = null;

    public void  testJob() {
        logger.info("定时任务启动start...");
        QueryWrapper<SysTaskConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("method","testJob").eq("bean_ref","jobTask");
        SysTaskConfig taskConfig = taskConfigService.getOne(queryWrapper);
        if (taskConfig != null && taskConfig.getThreads() > 1) {
            if (executorService == null || executorService.isShutdown()) {
                executorService = Executors.newFixedThreadPool(taskConfig.getThreads());
            }
            executorService.execute(new JobRunnable() {
                @Override
                public void doTask() {
                    logger.info("--------" + new Random().nextInt(100));
                }
            });
        }
        logger.info("定时任务启动end...");
    }
}
