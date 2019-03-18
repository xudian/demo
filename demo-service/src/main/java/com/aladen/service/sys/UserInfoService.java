package com.aladen.service.sys;


import com.aladen.entity.sys.UserInfoDO;

/**
 * @Title: UserInfoService
 * @Description: TODO
 * @Author xu
 * @Date 2018/8/9 下午3:59
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public interface UserInfoService {

    int saveEntity(UserInfoDO userInfo);

    int saveOrUpdateEntity(UserInfoDO userInfoDO);

    /**
     * @Description: 通过用户编号查询用户信息
     * @params: [userId]
     * @return: com.example.demo.entity.sys.UserInfoDO
     * @date: 2018/8/15 18:12
     */
    UserInfoDO queryUserInfo(int userId);

    /**
     * @Description: 通过用户账号查询用户信息
     * @params: [userName]
     * @return: com.example.demo.entity.sys.UserInfoDO
     * @date: 2018/8/22 14:39
     */
    UserInfoDO queryByName(String userName);
}
