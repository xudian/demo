package com.aladen.service.test;

/**
 * @Title: TestRedisCache
 * @Description:
 * @Author xu
 * @Date 2019/2/28 17:47
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public interface TestRedisCache {

    void testSkipe(String key, String orgKey);


    void threadRun();
}
