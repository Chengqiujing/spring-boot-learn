package com.chengqj.study.springbootredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    public void setString(String key,Object value){
        //序列化器
        RedisSerializer keySerializer = new StringRedisSerializer();
        RedisSerializer valSerializer = new GenericJackson2JsonRedisSerializer();

        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(valSerializer);

        redisTemplate.opsForValue().set(key,value);
    }
}
