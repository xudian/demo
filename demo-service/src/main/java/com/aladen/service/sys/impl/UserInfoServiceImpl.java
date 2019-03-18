package com.aladen.service.sys.impl;

import com.aladen.dao.sys.UserInfoDao;
import com.aladen.entity.sys.UserInfoDO;
import com.aladen.service.base.BaseService;
import com.aladen.service.sys.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Title: UserInfoServiceImpl
 * @Description: TODO
 * @Author xu
 * @Date 2018/8/9 下午4:06
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */

@Service
public class UserInfoServiceImpl extends BaseService implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public int saveEntity(UserInfoDO userInfo) {
        return userInfoDao.saveEntity(userInfo);
    }

    @Override
    public int saveOrUpdateEntity(UserInfoDO userInfoDO) {
        int result = 0;
        if (userInfoDO.getUserId() == null) {
            result = userInfoDao.saveEntity(userInfoDO);
        } else {
            userInfoDO.setUpdateTime(new Date());
            result = userInfoDao.updateEntity(userInfoDO);
        }
        Random random = new Random();
        int i = random.nextInt(10);
        logger.info("+++++++++i:{}", i);
        if (i % 2 == 0) {
            throw  new RuntimeException("主动抛出异常，看事务是否回滚");
        }
        return result;
    }

    @Override
    public UserInfoDO queryUserInfo(int userId) {
        Map<String,Object> paramsMap = new HashMap<>(1);
        paramsMap.put("userId",userId);
        return userInfoDao.queryByParams(paramsMap);
    }

    @Override
    public UserInfoDO queryByName(String userName) {
        Map<String,Object> paramsMap = new HashMap<>(1);
        paramsMap.put("userName",userName);
        return userInfoDao.queryByParams(paramsMap);
    }
}
