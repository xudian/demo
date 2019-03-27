package com.aladen.common.enumconstants;

/**
 * @Title: RespCodeEnum
 * @Description:
 * @Author xu
 * @Date 2019/3/26 16:00
 * @Version V1.0
 * @Copyright 2019 All Rights Reserved
 */
public enum RespCodeEnum  {
    SUCCESS("0000","操作成功"),
    SIGN_ERROR("1001","签名异常"),
    SERVER_ERROR("1002","系统异常"),
    NOBEAN_ERROR("1003","无此接口"),
    TOKEN_ERROR("1004","token异常");

    private String code;
    private String viewMsg;

    RespCodeEnum(String code, String viewMsg) {
        this.code = code;
        this.viewMsg = viewMsg;
    }

    public String getCode() {
        return code;
    }


    public String getViewMsg() {
        return viewMsg;
    }
}
