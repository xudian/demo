package com.aladen.service.test.impl;

import com.aladen.service.base.BaseService;
import com.aladen.service.redis.RedisService;
import com.aladen.service.redis.lock.RedisLock;
import com.aladen.service.test.CacheThread;
import com.aladen.service.test.TestRedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @Title: TestRedisCacheImpl
 * @Description:
 * @Author xu
 * @Date 2019/2/28 17:50
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
@Service
public class TestRedisCacheImpl extends BaseService implements TestRedisCache {

    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void testSkipe(String key, String orgKey) {
        try {
            RedisLock redisLock = new RedisLock(redisTemplate);
            if (redisLock.lock(orgKey)) {
                logger.info("进入到系统中key:{}",key);
                if (redisService.exists(orgKey) && Integer.valueOf(redisService.get(orgKey).toString()) > 0) {
                    // 设置已购买人员信息
                    redisService.hmSet(orgKey+"hm",key,System.currentTimeMillis());
                    redisService.incrby(orgKey,-1);
                    logger.info("用户key:{}操作成功",key);
                } else {
                    logger.info("orgkey不存在或为0,无法后续操作;key:{}",key);
                }
            } else {
                logger.info("用户未获取到权限，已被其它用户占用;key:{}",key);
            }
        } catch (Exception e) {
            logger.info("操作失败; key:{},e:{}",key,e);
        }
    }

    @Override
    public void threadRun() {
        ExecutorService executor = Executors.newFixedThreadPool(30);
        IntStream.range(1,1000).forEach(i -> {
            CacheThread cacheThread = new CacheThread(this,i+"","proA");
            executor.execute(cacheThread);
        });
    }
}
