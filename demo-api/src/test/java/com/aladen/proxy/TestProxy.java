package com.aladen.proxy;

import com.aladen.base.BaseTest;
import com.aladen.service.proxy.SealPhone;
import com.aladen.service.proxy.WebSite;
import com.aladen.service.proxy.impl.SealAndroid;
import com.aladen.service.proxy.impl.SealIphone;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Title: TestProxy
 * @Description:
 * @Author xu
 * @Date 2019/3/19 16:07
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public class TestProxy extends BaseTest {

    @Test
    public void test() {
        SealIphone seal = new SealIphone();
        InvocationHandler handler = new WebSite(seal);

        SealPhone sealPhone = (SealPhone) Proxy.newProxyInstance(SealIphone.class.getClassLoader(),SealIphone.class.getInterfaces(),handler);
        sealPhone.seal();

        SealAndroid android = new SealAndroid();
        InvocationHandler handler1 = new WebSite(android);

        SealPhone sealPhone1 = (SealPhone) Proxy.newProxyInstance(SealAndroid.class.getClassLoader(),SealAndroid.class.getInterfaces(),handler1);
        sealPhone1.seal();

        logger.info("proxyName:{}",sealPhone.getClass().getSimpleName());
        logger.info("proxyName1:{}",sealPhone1.getClass().getSimpleName());
    }
}
