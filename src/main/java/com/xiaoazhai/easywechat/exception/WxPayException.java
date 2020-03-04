package com.xiaoazhai.easywechat.exception;

public class WxPayException extends RuntimeException {

    public WxPayException(String msg) {
        super("微信支付异常:   " + msg);
    }
}
