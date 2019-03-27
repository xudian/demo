package com.aladen.api;

import com.aladen.api.base.BaseApiService;
import com.aladen.base.BaseController;
import com.aladen.common.helper.SpringContextHolder;
import com.aladen.common.properties.ApiProperties;
import com.aladen.service.redis.RedisService;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @Title: ApiController
 * @Description:
 * @Author xu
 * @Date 2019/3/26 09:40
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
@RestController
@RequestMapping("api")
public class ApiController extends BaseController {

    @Autowired
    private ApiProperties apiProperties;

    @Autowired
    private RedisService redisService;

    /**
     * token校验过滤
     */
    private static final Set<String> ALLOW_TOKEN = new HashSet<>();

    private static final Set<String> ALLOW_SIGN = new HashSet<>();

    static {
        ALLOW_TOKEN.add("");
    }

    static {
        ALLOW_SIGN.add("");
    }

    private boolean isTokenAllow(String ifn) {
        return ALLOW_TOKEN.contains(ifn);
    }

    private boolean isSignAllow(String ifn) {
        return ALLOW_SIGN.contains(ifn);
    }



    @RequestMapping(value ="{ifn}")
    @CrossOrigin(origins = "*")
    public JSONObject execute(@PathVariable String ifn, HttpServletRequest request) {
        JSONObject result = new JSONObject();
        logger.info("==============调用接口:{} start============================",ifn);
        printParams(request);
        boolean checkToken;
        if (apiProperties.isTokenOpen()) {
            if (isTokenAllow(ifn)) {
                checkToken = Boolean.TRUE;
            } else {
                checkToken = checkToken(request);
            }
        } else {
            checkToken = Boolean.TRUE;
        }
        logger.info("接口:{}，token校验结果:{}",ifn,checkToken);
        if (checkToken) {
            try {
                BaseApiService baseApiService = SpringContextHolder.getBean(ifn);
                boolean signFlag = Boolean.TRUE;
                if (isSignAllow(ifn) || !apiProperties.isSignOpen()) {
                    signFlag = Boolean.FALSE;
                }
                result = baseApiService.doBusiness(request,signFlag);
            } catch (Exception e) {
                logger.error("调用接口异常;ifn:{};e:{}",ifn,e);
            }
        } else {
            logger.error("接口token校验不通过;ifn:{}",ifn);
        }
        logger.info("调用接口:{},返回结果:{}",ifn,result);
        logger.info("==============调用接口:{} end============================",ifn);
        return result;
    }

    /**
     * @Description: 判断token有效性
     *         1、通过key，判断token是否存在;获取对应的用户信息
     *         2、通过用户信息判断用户对应的token是否一致
     * @params: [request]
     * @return: boolean
     * @date: 2019/3/26 14:22
     */
    @Override
    protected boolean checkToken(HttpServletRequest request) {
        boolean result = Boolean.FALSE;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            logger.error("签名为空");
            return result;
        }

        Object tokenMsg = redisService.get(TOKEN_KEY + token);
        if (tokenMsg == null) {
            logger.error("通过key无法查询到信息;token:{}",token);
            return result;
        }
        JSONObject tokenJson = JSONObject.parseObject(tokenMsg.toString());
        String phone = tokenJson.getString("phone");
        if (StringUtils.isNotBlank(phone)) {
            String tokenValue = String.valueOf(redisService.get(USER_KEY + phone));
            if (StringUtils.equals(token,tokenValue)) {
                result = Boolean.TRUE;
            }
        }
        return result;
    }
}
