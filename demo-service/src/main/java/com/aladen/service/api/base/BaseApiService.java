package com.aladen.service.api.base;

import com.aladen.common.enumconstants.RespCodeEnum;
import com.aladen.common.exception.BusiException;
import com.aladen.common.util.SignUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: BaseApiService
 * @Description:
 * @Author xu
 * @Date 2019/3/26 10:58
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public abstract class BaseApiService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected abstract JSONObject execute(HttpServletRequest request);

    protected boolean checkSign(HttpServletRequest request) {
        boolean result = false;
        String sign = request.getHeader("sign");
        if (StringUtils.isNotBlank(sign)) {
            result = SignUtil.checkSign(request,sign,"");
        } else {
            logger.error("sign数据不存在");
        }
        return result;
    }

    /**
     * @Description: 接口业务逻辑处理
     *          1、签名校验；(判断是否需要校验签名，需要则进行签名校验)
     *          2、业务逻辑处理
     * @params: [request, isSignOpen]
     * @return: com.alibaba.fastjson.JSONObject
     * @date: 2019/3/26 14:48
     */
    public JSONObject doBusiness(HttpServletRequest request,Boolean isSignOpen) {
        JSONObject result;
        if (isSignOpen) {
            isSignOpen = checkSign(request);
        } else {
            isSignOpen = Boolean.TRUE;
        }
        if (isSignOpen) {
            result = execute(request);
        } else {
            throw new BusiException(RespCodeEnum.SIGN_ERROR.getCode(),RespCodeEnum.SIGN_ERROR.getViewMsg());
        }
        return result;
    }

}
