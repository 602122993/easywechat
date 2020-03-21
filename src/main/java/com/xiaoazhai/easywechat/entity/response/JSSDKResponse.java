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
     * 时间戳
     */
    private Long timestamp;

    /**
     * 签名
     */
    private String sign;
    private String appid;

}
