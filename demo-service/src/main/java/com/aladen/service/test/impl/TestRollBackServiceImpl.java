package com.aladen.service.test.impl;

import com.aladen.entity.sys.UserInfoDO;
import com.aladen.service.base.BaseService;
import com.aladen.service.sys.UserInfoService;
import com.aladen.service.test.TestRollBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Title: TestRollBackServiceImpl
 * @Description: TODO
 * @Author xu
 * @Date 2018/9/12 10:16
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
@Service
public class TestRollBackServiceImpl extends BaseService implements TestRollBackService {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public int saveUser(String userName) {
        int result = 0;
        try {
            result = save(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int save(String userName) {
        UserInfoDO userInfo = userInfoService.queryByName(userName);
        if (userInfo == null) {
            userInfo = new UserInfoDO();
            userInfo.setUserName(userName);
            userInfo.setTrueName(userName);
            userInfo.setPassword("123456");
            userInfo.setCreateTime(new Date());
            userInfo.setUserStatus("0");
        }
        try {
            userInfoService.saveOrUpdateEntity(userInfo);
            logger.info("添加用户成功;userName:{}",userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
