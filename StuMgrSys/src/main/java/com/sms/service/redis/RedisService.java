package com.sms.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, Map<String,String>> redisTemplate;

    @Autowired
    private RedisTemplate<String, Long> redisLongTemplate;

    @Autowired
    private RedisTemplate<String, Integer> redisIntTemplate;

    @Autowired
    private RedisTemplate<String,String> RedisStringTemplate;
}
