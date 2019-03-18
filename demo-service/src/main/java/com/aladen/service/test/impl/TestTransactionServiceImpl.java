package com.aladen.service.test.impl;

import com.aladen.entity.sys.UserInfoDO;
import com.aladen.service.base.BaseService;
import com.aladen.service.sys.UserInfoService;
import com.aladen.service.test.TestRollBackService;
import com.aladen.service.test.TestTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @Title: TestTransactionServiceImpl
 * @Description: TODO
 * @Author xu
 * @Date 2018/9/9 11:30
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
@Service
public class TestTransactionServiceImpl extends BaseService implements TestTransactionService {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private TestRollBackService testRollBackService;

    @Override
    public int testTrans(String userName, long roleId) {
        // 启动一个新线程，保存用户角色信息
        CountDownLatch downLatch = new CountDownLatch(1);

        logger.info("给用户添加角色启动线程成功;userName:{},roleId:{}", userName, roleId);

        UserInfoDO userInfo = userInfoService.queryByName(userName);
        if (userInfo == null) {
            userInfo = new UserInfoDO();
            userInfo.setUserName(userName);
            userInfo.setTrueName("测试事物");
            userInfo.setPassword("123456");
            userInfo.setCreateTime(new Date());
            userInfo.setUserStatus("0");
        }
        userInfoService.saveOrUpdateEntity(userInfo);
        logger.info("添加用户成功;userName:{}",userName);
        downLatch.countDown();
        logger.info("打印日志，查看线程执行顺序");
        return 0;
    }

    @Override
    public int listUser(String[] userNames) {
        try {
            for (String userName : userNames) {
                testRollBackService.saveUser(userName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
