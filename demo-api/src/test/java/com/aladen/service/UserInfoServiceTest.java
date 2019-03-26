package com.aladen.service;

import com.aladen.DemoApplicationTests;
import com.aladen.entity.user.SysUserInfo;
import com.aladen.service.user.ISysUserInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

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
    private ISysUserInfoService userInfoService;

    @Test
    public void testSave(){
        SysUserInfo userInfo = new SysUserInfo();
        userInfo.setUserName("test0325");
        userInfo.setTrueName("测试事物1232");
        userInfo.setPassword("123456");
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setUserStatus("0");
//        userService.saveEntity(userInfo);
        userInfoService.save(userInfo);
    }

}
