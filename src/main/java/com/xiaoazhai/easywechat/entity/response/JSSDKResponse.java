package com.xiaoazhai.easywechat.entity.response;

import lombok.Data;

/**
 * js签名返回结果
 */
@Data
public class JSSDKResponse extends ErrorResponse {
    /**
     * 随机字符串
     */
    private String noncestr;
    /**
     * js票据
     */
    private String jsapiTicket;
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 授权地址
     */
    private String url;
    /**
     * 签名
     */
    private String sign;

}
