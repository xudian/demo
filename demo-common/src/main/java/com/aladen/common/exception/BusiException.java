package com.aladen.common.exception;

/**
 * @Title: BusiException
 * @Description:
 * @Author xu
 * @Date 2019/3/27 10:15
 * @Version V1.0
 * @Copyright 2019 All Rights Reserved
 */
public class BusiException extends RuntimeException {

    private String code ;
    private String msg;

    public BusiException(String message) {
        super(message);
        this.msg = message;
    }

    public BusiException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
