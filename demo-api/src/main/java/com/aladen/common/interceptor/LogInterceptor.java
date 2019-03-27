package com.aladen.common.interceptor;

import com.aladen.common.util.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Title: LogInterceptor
 * @Description:
 * @Author xu
 * @Date 2019/3/26 17:03
 * @Version V1.0
 * @Copyright 2019 All Rights Reserved
 */
@Configuration
public class LogInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    private static final String REQUEST_ID = "requestId";
    private static final String IP = "ip";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String remoteIp = IPUtil.getRemoteIP(request);
        String uuid = UUID.randomUUID().toString().replace("-","").toLowerCase();
        MDC.put(IP,remoteIp);
        MDC.put(REQUEST_ID,uuid);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        MDC.remove(IP);
        MDC.remove(REQUEST_ID);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
