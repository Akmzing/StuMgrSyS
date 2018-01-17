package com.sms.filter;

import com.sms.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestFilter extends OncePerRequestFilter {

    @Autowired
    RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("##########before");
        filterChain.doFilter(httpServletRequest,httpServletResponse);
        System.out.println("##########after");
    }
}
