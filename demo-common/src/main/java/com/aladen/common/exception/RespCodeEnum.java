package com.aladen.common.exception;

/**
 * @Title: RespCodeEnum
 * @Description:
 * @Author xu
 * @Date 2019/3/26 16:00
 * @Version V1.0
 * @Copyright 2019 All Rights Reserved
 */
public enum RespCodeEnum implements CommonExceptionEnum {
    SUCCESS("0000","操作成功"),
    SIGN_ERROR("1001","签名异常"),
    SERVER_ERROR("1002","系统异常");

    private String code;
    private String viewMsg;

    RespCodeEnum(String code, String viewMsg) {
        this.code = code;
        this.viewMsg = viewMsg;
    }

    @Override
    public String getCode() {
        return code;
    }


    @Override
    public String getViewMsg() {
        return viewMsg;
    }
}
