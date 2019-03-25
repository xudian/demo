package com.aladen.service.proxy;

import com.aladen.service.base.BaseService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Title: WebSite
 * @Description:
 * @Author xu
 * @Date 2019/3/19 16:01
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public class WebSite extends BaseService implements InvocationHandler {

    private Object phone;

    public WebSite(Object phone) {
        this.phone = phone;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("官网地址:{}",this.getClass().getSimpleName());
        method.invoke(phone,args);
        logger.info("官网开始预售手机");
        return null;
    }
}
