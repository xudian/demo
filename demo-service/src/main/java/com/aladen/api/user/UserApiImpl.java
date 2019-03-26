package com.aladen.api.user;

import com.aladen.api.base.BaseApiService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: UserApiImpl
 * @Description:
 * @Author xu
 * @Date 2019/3/26 11:04
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
@Service("userRegister")
public class UserApiImpl extends BaseApiService {


    @Override
    public JSONObject execute(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        logger.info("进入方法;");
        return result;
    }
}
