package com.sms.interceptor;

import com.sms.model.CourseEntity;
import com.sms.repository.CourseRepository;
import com.sms.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class TestInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        redisTemplate.opsForValue().set("sessionId","1111");
        System.out.println("sessionId "+ redisTemplate.opsForValue().get("sessionId"));

        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("post");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("after");
    }
}
