package com.xiaoazhai.easywechat.entity.response;

import lombok.Data;

/**
 * @author zhai
 * @date 2020年2月22日18:57:00
 * <p>
 * 微信支付返回结果
 * </p>
 */
@Data
public class WxPayResponse  extends BaseResponse {
    /**
     * 微信appid
     */
    private String appid;
    /**
     * 微信商户id
     */
    private String mchId;
    /**
     * 设备号
     */
    private String deviceInfo;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;
    /**
     * 返回code;
     */
    private String resultCode;
    /**
     * 错误码
     */
    private String errCode;
    /**
     * 错误码描述
     */
    private String errCodeDes;
    /**
     * 交易类型
     */
    private String tradeType;
    /**
     * 预交易id
     */
    private String prepayId;
    /**
     * 二维码链接
     */
    private String codeUrl;
    /**
     * 微信跳转支付地址
     */
    private String mwebUrl;

}
