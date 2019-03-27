package com.aladen.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
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
        for(String key :map.keySet()){
            String[] values = map.get(key);
            String log = "参数:"+key + "=";
            String logValue = null;
            for(int i = 0 ;i < values.length ; i++){
                if(logValue == null){
                    logValue = values[i] ;
                }else{
                    logValue = logValue + "," +values[i];
                }
            }
            logger.info(log+logValue);
        }
    }

    protected abstract boolean checkToken(HttpServletRequest request);
}
