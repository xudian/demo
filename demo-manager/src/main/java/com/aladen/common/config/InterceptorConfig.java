package com.aladen.common.config;

import com.aladen.common.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("");
    }

    /**
     * @Description: 访问跟目录默认跳转地址
     * @params: [registry]
     * @return: void
     * @date: 2018/8/23 14:19
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:main");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }


}
