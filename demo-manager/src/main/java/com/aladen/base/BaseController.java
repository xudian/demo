package com.aladen.base;

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
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected void  printParams(HttpServletRequest request){
        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String,String[]> m :map.entrySet()){
            logger.info("参数:{}={}",m.getKey(), String.join(",",Arrays.asList(m.getValue())));
        }
    }

}
