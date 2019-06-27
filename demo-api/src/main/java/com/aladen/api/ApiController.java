package com.aladen.api;

import com.aladen.base.BaseController;
import com.aladen.common.annotation.CheckParams;
import com.aladen.common.constants.RedisConstants;
import com.aladen.common.enumconstants.RespCodeEnum;
import com.aladen.common.exception.BusiException;
import com.aladen.common.helper.SpringContextHolder;
import com.aladen.common.util.EnvironmentUtil;
import com.aladen.common.util.ResponseModel;
import com.aladen.service.api.base.BaseApiService;
import com.aladen.service.redis.RedisService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        JSONObject result;
        logger.info("==============调用接口:{} start============================",ifn);
        printParams(request);
        boolean checkToken;
        if (Boolean.valueOf(EnvironmentUtil.getProperty(key_tokenOpen))) {
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
                if (isSignAllow(ifn) || !Boolean.valueOf(EnvironmentUtil.getProperty(key_signOpen))) {
                    signFlag = Boolean.FALSE;
                }
                // 注解参数校验
                boolean paramsFlag = checkParams(ifn,baseApiService,request);

                if (!paramsFlag) {
                    result = ResponseModel.retFail(RespCodeEnum.PARAMS_ERROR);
                } else {
                    result = ResponseModel.retSuccess(RespCodeEnum.SUCCESS,baseApiService.doBusiness(request,signFlag));
                }
            } catch (NoSuchBeanDefinitionException bde) {
                logger.error("调用接口异常,接口不存在;ifn:{};e:{}",ifn,bde.getMessage());
                result = ResponseModel.retFail(RespCodeEnum.NOBEAN_ERROR);
            } catch (BusiException be) {
                logger.error("调用接口异常,业务逻辑抛出异常;ifn:{};e:{}",ifn,be.getMsg());
                String code = be.getCode();
                if (StringUtils.isBlank(be.getCode())) {
                    code = RespCodeEnum.SERVER_ERROR.getCode();
                }
                result = ResponseModel.retFail(code,be.getMsg());
            } catch (Exception e) {
                logger.error("调用接口异常;ifn:{};e:{}",ifn,e.getMessage());
                result = ResponseModel.retFail(RespCodeEnum.SERVER_ERROR);
            }
        } else {
            logger.error("接口token校验不通过;ifn:{}",ifn);
            result = ResponseModel.retFail(RespCodeEnum.TOKEN_ERROR);
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

        Object tokenMsg = redisService.get(RedisConstants.TOKEN_KEY + token);
        if (tokenMsg == null) {
            logger.error("通过key无法查询到信息;token:{}",token);
            return result;
        }
        JSONObject tokenJson = JSONObject.parseObject(tokenMsg.toString());
        String phone = tokenJson.getString("phone");
        if (StringUtils.isNotBlank(phone)) {
            String tokenValue = String.valueOf(redisService.get(RedisConstants.USER_KEY + phone));
            if (StringUtils.equals(token,tokenValue)) {
                result = Boolean.TRUE;
            }
        }
        return result;
    }

    @Override
    protected boolean checkParams(String ifn,BaseApiService baseApiService,HttpServletRequest request) {
        boolean result = true;
        CheckParams checkParams = baseApiService.getClass().getAnnotation(CheckParams.class);
        List<String> nullList = null;
        if (checkParams != null) {
            String[] values = checkParams.value();
            if (values.length > 0) {
                nullList = new ArrayList<>(values.length);
                for (String key : values) {
                    String value = request.getParameter(key);
                    if (StringUtils.isBlank(value)) {
                        nullList.add(key);
                    }
                }
            }
        }
        if (nullList != null && nullList.size() > 0) {
            logger.info("调用接口:{} 参数校验不通过;参数:{} 为空",ifn,nullList);
            result = false;
        }
        return result;
    }
}
