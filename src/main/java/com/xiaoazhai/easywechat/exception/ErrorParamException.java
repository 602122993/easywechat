package com.xiaoazhai.easywechat.exception;

/**
 * 参数错误异常
 */
public class ErrorParamException extends RuntimeException {

    public ErrorParamException(String errorMsg) {
        super("参数错误异常," + errorMsg );
    }


}
