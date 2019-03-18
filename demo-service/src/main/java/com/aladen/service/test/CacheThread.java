package com.aladen.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: CacheThread
 * @Description:
 * @Author xu
 * @Date 2019/2/28 19:14
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public class CacheThread implements Runnable {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private TestRedisCache testRedisCache;
    private String key;
    private String proKey;

    public CacheThread(TestRedisCache testRedisCache, String key, String proKey) {
        this.testRedisCache = testRedisCache;
        this.key = key;
        this.proKey = proKey;
    }

    @Override
    public void run() {
        logger.info("testRedisCache:{}",testRedisCache);
        testRedisCache.testSkipe(key,proKey);
    }
}
