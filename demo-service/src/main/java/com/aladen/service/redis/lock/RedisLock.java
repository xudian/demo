package com.aladen.service.redis.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Title: RedisLock
 * @Description:
 * @Author xu
 * @Date 2019/2/28 17:20
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public class RedisLock {

    private static Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private static final long DEFAULT_WAIT_TIME_OUT = 3;

    private static final long DEFAULT_EXPIRE_TIME_OUT = 3;

    private static final String LOCK_KEY = "lock:";

    private RedisTemplate redisTemplate;

    public RedisLock(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean lock(String key, long wait, long expire, TimeUnit seconds) {
        String lockKey = generateKey(key);
        long nanoWaitForLock = seconds.toNanos(expire);
        long start = System.nanoTime();

        try {
            while ((System.nanoTime() - start) < nanoWaitForLock) {
                if (redisTemplate.getConnectionFactory().getConnection().setNX(lockKey.getBytes(), new byte[0])) {
                    redisTemplate.expire(lockKey, expire, seconds);
                    logger.debug("add RedisLock[{}].{}", key, Thread.currentThread());
                    return true;
                }
                /**
                    * 防止饥饿进程的出现,即,当同时到达多个进程,
                    * 只会有一个进程获得锁,其他的都用同样的频率进行尝试,后面有来了一些进行,也以同样的频率申请锁,这将可能导致前面来的锁得不到满足.
                    * 使用随机的等待时间可以一定程度上保证公平性
                 */
                TimeUnit.MILLISECONDS.sleep(100 + new Random().nextInt(100));
            }
        } catch (Exception e) {
            logger.error(LOCK_KEY + "{}", e.getMessage(), e);
            unlock(key);
        }
        return false;
    }

    /**
     * 释放锁
     */
    public boolean unlock(String key) {
        try {
            String lockKey = generateKey(key);
            RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
            connection.del(lockKey.getBytes());
            connection.close();
            return true;
        } catch (Exception e) {
            logger.error("{},{}", e.getMessage(), e);
        }
        return false;
    }

    public boolean lock(String key) {
        return lock(key,DEFAULT_WAIT_TIME_OUT,DEFAULT_EXPIRE_TIME_OUT,TimeUnit.SECONDS);
    }

    public String generateKey(String key){
        return LOCK_KEY + key;
    };
}
