package com.aladen.common;

import com.aladen.base.BaseTest;
import com.aladen.entity.sys.UserInfoDO;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Title: TestClass
 * @Description:
 * @Author xu
 * @Date 2019/3/19 09:53
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public class TestClass extends BaseTest {

    @Test
    public void testClassInvoke() {
        logger.info("====================== 1.0 =================================");
        // 1.0 获取类名
        Class aclass = UserInfoDO.class;
        logger.info("class Name:{};simpleName:{}",aclass.getName(),aclass.getSimpleName());
        logger.info("====================== 2.0 =================================");
        // 2.0 获取public权限的成员变量(子类和父类)
        Field[] fields = aclass.getFields();
        logger.info("fields:{}",fields);
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                logger.info("name:{}",field.getType().getName(),field.getName());
            }
        }
        logger.info("====================== 3.0 =================================");
        // 3.0 获取所有的成员变量
        Field[] classFields = aclass.getDeclaredFields();
        logger.info("classFields:{}",classFields);
        if (classFields != null && classFields.length > 0) {
            for (Field field : classFields) {
                logger.info("type:{};name:{}",field.getType().getName(),field.getName());
            }
        }
        logger.info("====================== 4.0 =================================");
        // 4.0 获取所有的方法信息
        Method[] methods = aclass.getMethods();
        logger.info("methods:{}",methods);
        if (methods != null && methods.length > 0) {
            for (Method method : methods) {
                logger.info("type:{};name:{}",method.getName());
            }
        }
    }
}
