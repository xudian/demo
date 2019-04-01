package com.aladen.base;

import com.aladen.service.api.base.BaseApiService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * @Title: BaseController
 * @Description:
 * @Author xu
 * @Date 2019/3/26 09:38
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static final String TOKEN_KEY = "token:";
    protected static final String USER_KEY = "token:";

    protected void  printParams(HttpServletRequest request){
        Map<String, String[]> map = request.getParameterMap();
        if (!map.isEmpty()) {
            for (Map.Entry<String,String[]> m : map.entrySet()) {
                String value = String.join(",",Arrays.asList(m.getValue()));
                logger.info("参数:{}={}",m.getKey(),value);
            }
        }
    }

    protected abstract boolean checkToken(HttpServletRequest request);

    protected abstract boolean checkParams(String ifn, BaseApiService baseApiService,HttpServletRequest request);
}
