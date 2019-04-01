package com.aladen.service.api.user;

import com.aladen.common.annotation.CheckParams;
import com.aladen.common.enumconstants.RespCodeEnum;
import com.aladen.common.exception.BusiException;
import com.aladen.entity.user.SysUserInfo;
import com.aladen.service.api.base.BaseApiService;
import com.aladen.service.user.ISysUserInfoService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @Title: UserApiImpl
 * @Description:
 * @Author xu
 * @Date 2019/3/26 11:04
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
@Service("userRegister")
@CheckParams(value = {"name","age","idCard"})
public class UserApiImpl extends BaseApiService {

    @Autowired
    private ISysUserInfoService userInfoService;


    @Override
    public JSONObject execute(HttpServletRequest request) {
        JSONObject result;
        SysUserInfo userInfo = new SysUserInfo();
        userInfo.setUserName("test032701");
        userInfo.setTrueName("测试事物1232");
        userInfo.setPassword("123456");
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setUserStatus("0");
        boolean saveFlag = userInfoService.save(userInfo);
        logger.info("保存数据成功;saveFlag:{}",saveFlag);
        if (2 > 1) {
            throw new BusiException(RespCodeEnum.SERVER_ERROR.getViewMsg());
        }
        return result;
    }
}
