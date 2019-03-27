package com.aladen.task.base;

import com.aladen.common.helper.SpringContextHolder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.Method;

/**
 * @Title: BaseJob
 * @Description:
 * @Author xu
 * @Date 2019/3/27 15:11
 * @Version V1.0
 * @Copyright 2019 All Rights Reserved
 */
public class BaseJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String bean = jobExecutionContext.getTrigger().getKey().getGroup();
        String method = jobExecutionContext.getTrigger().getKey().getName();

        Object job = SpringContextHolder.getBean(bean);
        try {
            Method doJob = job.getClass().getDeclaredMethod(method);
            doJob.setAccessible(Boolean.TRUE);
            doJob.invoke(job);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
