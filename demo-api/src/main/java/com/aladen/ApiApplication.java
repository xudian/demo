package com.aladen;

import com.aladen.common.listener.MyEventListener;
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
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ApiApplication.class);
        application.addListeners(new MyEventListener());
        application.run(args);
    }
}
