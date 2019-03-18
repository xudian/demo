package com.aladen.dao;

import com.aladen.DemoApplicationTests;
import com.aladen.dao.sys.UserInfoDao;
import com.aladen.entity.sys.UserInfoDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class UserInfoDaoTest extends DemoApplicationTests {

    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    public void testSave(){
        UserInfoDO userInfo = new UserInfoDO();
        userInfo.setUserName("testUser");
        userInfo.setTrueName("测试用户信息");
        userInfo.setPassword("123111");
        userInfo.setCreateTime(new Date());
        userInfo.setUserStatus("0");
        userInfoDao.saveEntity(userInfo);
    }
}
