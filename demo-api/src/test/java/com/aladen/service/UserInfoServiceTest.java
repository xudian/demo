package com.aladen.service;

import com.aladen.DemoApplicationTests;
import com.aladen.entity.sys.UserInfoDO;
import com.aladen.service.sys.UserInfoService;
import com.aladen.service.test.TestTransactionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Title: UserInfoServiceTest
 * @Description: TODO
 * @Author xu
 * @Date 2018/8/9 下午4:17
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public class UserInfoServiceTest extends DemoApplicationTests {

    @Autowired
    private UserInfoService userService;

    @Autowired
    private TestTransactionService transService;

    @Test
    public void testSave(){
        UserInfoDO userInfo = new UserInfoDO();
        userInfo.setUserName("test123");
        userInfo.setTrueName("测试事物1232");
        userInfo.setPassword("123456");
        userInfo.setCreateTime(new Date());
        userInfo.setUserStatus("0");
        userService.saveEntity(userInfo);
    }

    @Test
    public void testTrans(){
        transService.testTrans("ma",108);
    }

    @Test
    public void testUSerAdd(){
        String[] names = {"zhangsan","lisi","wangwu"};
        transService.listUser(names);
    }
}
