package com.aladen.common;

import com.aladen.base.BaseTest;
import com.aladen.common.util.DateUtil;
import com.aladen.service.api.base.BaseApiService;
import com.aladen.service.api.user.UserApiImpl;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @Title: ReflectTest
 * @Description:
 * @Author xu
 * @Date 2019/4/1 11:37
 * @Version V1.0
 * @Copyright 2019 All Rights Reserved
 */
public class ReflectTest extends BaseTest {

    @Test
    public void test(){
        BaseApiService baseApiService = new UserApiImpl();
        Method[] methods = baseApiService.getClass().getDeclaredMethods();
        if (methods.length > 0) {
            for (Method method : methods)
            logger.info("method:{}",method.getName());
        }

    }

    @Test
    public void testDate() {
        logger.info("now:{}", DateUtil.getNow());
        logger.info("queryNow:{}", DateUtil.getQueryNow());
        logger.info("parse:{}",DateUtil.parse("2019-04-04 15:32:16",DateUtil.NORM_DATETIME_PATTERN));
    }

}
