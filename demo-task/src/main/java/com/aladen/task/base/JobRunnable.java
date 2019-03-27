package com.aladen.task.base;

/**
 * @Title: JobRunnable
 * @Description:
 * @Author xu
 * @Date 2019/3/27 15:29
 * @Version V1.0
 * @Copyright 2019 All Rights Reserved
 */
public abstract class JobRunnable implements Runnable {

    @Override
    public void run() {
        doTask();
    }

    public abstract void doTask();
}
