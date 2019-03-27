package com.aladen.service;

import com.aladen.DemoApplicationTests;
import com.aladen.service.redis.RedisService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Title: RedisServiceTest
 * @Description: testRedis
 * @Author xu
 * @Date 2018/9/9 14:44
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public class RedisServiceTest extends DemoApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    public void testRedis(){
        String key = "proA";
        String val = "30";
        redisService.set(key,val);
        String value = (String) redisService.get("proA");
        logger.info("-----------------value:{}", value);
    }

    @Test
    public void testLPop() {
        String value = (String)redisService.lpop("subject");
        logger.info("subject:{}",value);
        List<Object> list = redisService.lRange("subject",0l,-1l);
        if (!list.isEmpty()) {
            list.forEach(l -> logger.info("subject-for:{}",l));
        }
    }

    @Test
    public void testSetNx() {
        boolean result = redisService.setnx("name","dingdong2",100l);
        logger.info("-----------------result:{}", result);
    }

    @Test
    public void testIncr() {
        redisService.incrby("proA",-1);
    }

}
