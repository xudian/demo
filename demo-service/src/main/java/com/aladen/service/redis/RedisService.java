package com.aladen.service.redis;

import java.util.List;
import java.util.Set;

/**
 * @Title: RedisService
 * @Description:
 * @Author xu
 * @Date 2018/9/9 14:18
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public interface RedisService {

    /**
     * @Description: 写入缓存
     * @params: [key, value]
     * @return: boolean
     * @date: 2018/9/9 14:19
     */
    boolean set(final String key, Object value);

    /**
     * @Description: 写入缓存，设置有效时间
     * @params: [key, value, expireTime]
     * @return: boolean
     * @date: 2018/9/9 14:20
     */
    boolean set(final String key, Object value, Long expireTime);

    /**
     * @Description: 批量删除key
     * @params: [keys]
     * @return: void
     * @date: 2018/9/9 14:20
     */
    void remove(final String... keys);

    /**
     * @Description: 删除对应的key
     * @params: [key]
     * @return: void
     * @date: 2018/9/9 14:23
     */
    void remove(final String key);

    /**
     * @Description: 判断是否有对应的value
     * @params: [key]
     * @return: boolean
     * @date: 2018/9/9 14:24
     */
    boolean exists(final String key);

    /**
     * @Description: 读取数据
     * @params: [key]
     * @return: java.lang.Object
     * @date: 2018/9/9 14:25
     */
    Object get(final String key);

    /**
     * @Description: 哈希 添加
     * @params: [key, hashKey, value]
     * @return: void
     * @date: 2018/9/9 14:26
     */
    void hmSet(String key, Object hashKey, Object value);

    /**
     * @Description: 哈希获取数据
     * @params: [key, hashKey]
     * @return: java.lang.Object
     * @date: 2018/9/9 14:29
     */
    Object hmGet(String key, Object hashKey);

    /**
     * @Description: 列表添加
     * @params: [k, v]
     * @return: void
     * @date: 2018/9/9 14:30
     */
    void lPush(String k, Object v);

    /**
     *  列表弹出
     * @param k
     * @return
     */
    Object lpop(String k);


    /**
     * @Description: 列表获取数据
     * @params: [k, l, l1]
     * @return: java.util.List<java.lang.Object>
     * @date: 2018/9/9 14:30
     */
    List<Object> lRange(String k, long l, long l1);

    /**
     * @Description: 集合添加
     * @params: [key, value]
     * @return: void
     * @date: 2018/9/9 14:31
     */
    void add(String key, Object value);

    /**
     * @Description: 集合获取
     * @params: [key]
     * @return: java.util.Set<java.lang.Object>
     * @date: 2018/9/9 14:32
     */
    Set<Object> setMembers(String key);

    /**
     * @Description: 有序集合添加
     * @params: [key, value, scoure]
     * @return: void
     * @date: 2018/9/9 14:33
     */
    void zAdd(String key, Object value, double scoure);

    /**
     * @Description: 有序集合获取
     * @params: [key, scoure, scoure1]
     * @return: java.util.Set<java.lang.Object>
     * @date: 2018/9/9 14:33
     */
    Set<Object> rangeByScore(String key, double scoure, double scoure1);

    /**
     * @Description: 插入数据，如果不存在则插入数据，存在，则不做任何处理
     * @params: [key, value]
     * @return: boolean
     * @date: 2018/9/11 10:45
     */
    boolean setnx(final String key, Object value);

    /**
     * @Description: 插入数据并设置有效时间(单位：秒) 如果不存在则插入数据，存在，则不做任何处理
     * @params: [key, value, expireTime]
     * @return: boolean
     * @date: 2018/9/11 11:32
     */
    boolean setnx(final String key, Object value, long expireTime);

    long pfadd(String k, Object v);

    Object incr(String key);

    Object incrby(String key, long num);
}
