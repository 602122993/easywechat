package com.xiaoazhai.easywechat.entity.response;

import lombok.Data;

/**
 * @author zhai
 * @date 2020年3月3日13:40:20
 * 微信付款到零钱返回结果
 */
@Data
public class WxPayToWalletResponse extends ErrorResponse {

    /**
     * 商户账号appid
     */
    private String mchAppid;
    /**
     * 商户id
     */
    private String mchid;
    /**
     * 设备号
     */
    private String deviceInfo;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 商户订单号
     */
    private String partnerTradeNo;
    /**
     * 微信付款单号
     */
    private String paymentNo;
    /**
     * 付款成功时间
     */
    private String paymentTime;

}
