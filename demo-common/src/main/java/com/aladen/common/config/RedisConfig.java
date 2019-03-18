package com.aladen.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Title: RedisConfig
 * @Description: redis 配置信息
 * @Author xu
 * @Date 2018/9/9 14:02
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * @Description: 管理缓存
     * @params: [redisTemplate]
     * @return: org.springframework.cache.CacheManager
     * @date: 2018/9/9 14:09
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory factory){
        return RedisCacheManager.create(factory);
    }

    /**
     * @Description: redisTemplate 配置
     * @params: [factory]
     * @return: org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.String>
     * @date: 2018/9/9 14:15
     */
    @Bean
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
