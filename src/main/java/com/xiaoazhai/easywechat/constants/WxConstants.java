package com.xiaoazhai.easywechat.constants;

/**
 * @author zhai
 * @date 2020年2月23日19:21:03
 * <p>
 * 微信支付常量
 * </p>
 */
public class WxConstants {

    /**
     * 统一下单
     */
    public static final String UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 获取access_token认证地址
     */
    public static final String AUTH_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    /**
     * 获取微信用户信息
     */
    public static final String USER_INFO = "https://api.weixin.qq.com/sns/userinfo";
    /**
     * 刷新accesstoken
     */
    public static final String REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
    /**
     * 发送红包
     */
    public static final String SEND_REDPACK = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
    /**
     * 微信付款到零钱
     */
    public static final String PAY_TO_WALLET = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

    /**
     * 获取基础支持access_token
     */
    public static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";
    /**
     * 获取微信jsticket
     */
    public static final String JS_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
    /**
     * 发送模板消息
     */
    public static final String SEND_TEMPLATE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send";
    /**
     * 获取用户信息
     */
    public static final String USE_INFO = "https://api.weixin.qq.com/cgi-bin/user/info";

}
