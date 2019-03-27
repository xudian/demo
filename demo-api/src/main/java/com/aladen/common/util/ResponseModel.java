package com.aladen.common.util;

import com.aladen.common.enumconstants.RespCodeEnum;
import com.aladen.common.enumconstants.StatusEnum;
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

    public static JSONObject retSuccess(RespCodeEnum exceptionEnum, JSONObject body) {
        JSONObject result = new JSONObject();
        JSONObject head = new JSONObject();
        head.put("code",exceptionEnum.getCode());
        head.put("msg",exceptionEnum.getViewMsg());
        result.put("head",head);
        if (body != null) {
            result.put("body",body);
        }
        result.put("status", StatusEnum.S.toString());
        return result;
    }

    public static JSONObject retFail(RespCodeEnum exceptionEnum) {
        JSONObject result = new JSONObject();
        JSONObject head = new JSONObject();
        head.put("code",exceptionEnum.getCode());
        head.put("msg",exceptionEnum.getViewMsg());
        result.put("head",head);
        result.put("status", StatusEnum.F.toString());
        return result;
    }

    public static JSONObject retFail(String code,String msg) {
        JSONObject result = new JSONObject();
        JSONObject head = new JSONObject();
        head.put("code",code);
        head.put("msg",msg);
        result.put("head",head);
        result.put("status", StatusEnum.F.toString());
        return result;
    }


}
