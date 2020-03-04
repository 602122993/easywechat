package com.xiaoazhai.easywechat.exception;

public class WxPubException extends RuntimeException {

    public WxPubException(String msg) {
        super("微信公众号异常:  " + msg);
    }

}
