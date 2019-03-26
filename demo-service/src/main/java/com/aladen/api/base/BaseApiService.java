package com.aladen.api.base;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: BaseApiService
 * @Description:
 * @Author xu
 * @Date 2019/3/26 10:58
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public abstract class BaseApiService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract JSONObject execute(HttpServletRequest request);


}
