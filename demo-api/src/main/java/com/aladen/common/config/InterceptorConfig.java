package com.aladen.common.config;

import com.aladen.common.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @Title: WebConfig
 * @Description:
 * @Author xu
 * @Date 2018/8/16 10:42
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor).addPathPatterns("/api/**");
    }
}
