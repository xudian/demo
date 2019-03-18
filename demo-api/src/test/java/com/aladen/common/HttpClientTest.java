package com.aladen.common;

import com.aladen.base.BaseTest;
import com.aladen.common.httpclient.HttpClientUtil;
import com.aladen.entity.sys.UserInfoDO;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: HttpClientTest
 * @Description: TODO
 * @Author xu
 * @Date 2018/8/23 18:37
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public class HttpClientTest extends BaseTest {

    @Test
    public void test(){
        String url = "http://localhost:81/app/api/init";
        String content = "orgId=514&proType=25&sign=1c0490063632ffd3e42c183560b9f6a5";
        String result = HttpClientUtil.httpGet(url,content);
        logger.info("result:{}",result);
    }

    @Test
    public void testPost(){
        String url = "http://localhost:81/app/api/init";
        String content = "orgId=514&proType=25&sign=1c0490063632ffd3e42c183560b9f6a5";
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("orgId","1232");
        paramsMap.put("proType","25");
        String result = HttpClientUtil.httpClientPost(url, paramsMap);
        String jsonResult = HttpClientUtil.httpClientPost(url, content);
        logger.info("result:{}",result);
        logger.info("jsonResult:{}",jsonResult);
    }


    @Test
    public void testEquals() {
        UserInfoDO user1 = new UserInfoDO();
        user1.setUserName("123");
        UserInfoDO user2 = new UserInfoDO();
        user2.setUserName("123");
        logger.info("userInfo:{}",user1.equals(user2));
    }
}
