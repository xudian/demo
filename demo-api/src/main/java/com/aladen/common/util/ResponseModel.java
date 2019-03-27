package com.aladen.common.util;

import com.aladen.common.exception.CommonExceptionEnum;
import com.alibaba.fastjson.JSONObject;

/**
 * @Title: ResponseModel
 * @Description:
 * @Author xu
 * @Date 2019/3/26 16:08
 * @Version V1.0
 * @Copyright 2019 All Rights Reserved
 */
public class ResponseModel {

    public static JSONObject retSuccess(CommonExceptionEnum exceptionEnum,JSONObject body) {
        JSONObject result = new JSONObject();
        JSONObject head = new JSONObject();
        head.put("code",exceptionEnum.getCode());
        head.put("msg",exceptionEnum.getViewMsg());
        result.put("head",head);
        if (body != null) {
            result.put("body",body);
        }
        return result;
    }

    public static JSONObject retFail(CommonExceptionEnum exceptionEnum) {
        JSONObject result = new JSONObject();
        JSONObject head = new JSONObject();
        head.put("code",exceptionEnum.getCode());
        head.put("msg",exceptionEnum.getViewMsg());
        result.put("head",head);
        return result;
    }


}
