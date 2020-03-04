package com.xiaoazhai.easywechat.entity.response;

import lombok.Data;

/**
 * @author zhai
 * @date 2020年2月28日11:54:04
 * 微信红包返回体
 */
@Data
public class WxRedPackResponse extends ErrorResponse {
    /**
     * 返回状态吗
     */
    private String returnCode;
    /**
     * 返回内容
     */
    private String returnMsg;
    /**
     * 业务结果
     */
    private String resultCode;
    /**
     * 商户订单号
     */
    private String mchBillno;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * 公众账号apid
     */
    private String wxappid;
    /**
     * 用户openid
     */
    private String reOpenid;
    /**
     * 付款金额
     */
    private String totalAmount;
    /**
     * 微信单号
     */
    private String sendListid;
}
