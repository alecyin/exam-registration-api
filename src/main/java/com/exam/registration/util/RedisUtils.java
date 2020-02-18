package com.exam.registration.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @author yhf
 * @classname RedisUtils
 **/
@Component
public class RedisUtils {
    public static final String STUDENT_TOKEN_PREFIX = "student_token:";
    public static final String ADMIN_TOKEN_PREFIX = "admin_token:";
    public static final String EXAM_NUMBER_PREFIX = "e_m:";
    private static RedisTemplate redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();//序列化为String
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    public static RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }
}
