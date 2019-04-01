package com.aladen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.DependsOn;

/**
 * @Title: DemoApplication
 * @Description:
 * @Author xu
 * @Date 2019/3/15 17:29
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
@SpringBootApplication
@DependsOn(value = {"springContextHolder"})
public class TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
}
