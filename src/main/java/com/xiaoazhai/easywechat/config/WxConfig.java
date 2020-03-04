package com.xiaoazhai.easywechat.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;


/**
 * @author zhai
 * @date 2020年2月21日13:29:10
 * <p>
 * 微信配置
 * </p>
 */
@Component
@ConfigurationProperties(prefix = "wx")
public class WxConfig {

    /**
     * 微信app的appid
     */
    public static String appId;
    /**
     * 微信app的APPsecret
     */
    public static String appSecret;
    /**
     * 微信app的商户id
     */
    public static String appMchId;
    /**
     * 微信app的商户秘钥
     */
    public static String appMchSecret;
    /**
     * 商户号id
     */
    public static String mchId;
    /**
     * 商户秘钥
     */
    public static String mchSecret;
    /**
     * 微信小程序的appId
     */
    public static String weAppId;
    /**
     * 微信小程序的秘钥
     */
    public static String weAppSecret;
    /**
     * 微信小程序的商户id
     */
    public static String weAppMchId;
    /**
     * 微信小程序的商户秘钥
     */
    public static String weAppMchSecret;
    /**
     * 微信公众号的appId
     */
    public static String pubAppId;
    /**
     * 微信公众号的秘钥
     */
    public static String pubAppSecret;
    /**
     * 微信公众号商户id
     */
    public static String pubMchId;
    /**
     * 微信公众号商户秘钥
     */
    public static String pubMchSecret;

    @PostConstruct
    public void init() {
        if (StringUtils.isEmpty(appMchId)) {
            appMchId = mchId;
        }
        if (StringUtils.isEmpty(appMchSecret)) {
            appMchSecret = mchSecret;
        }
        if (StringUtils.isEmpty(weAppMchId)) {
            weAppMchId = mchId;
        }
        if (StringUtils.isEmpty(weAppMchSecret)) {
            weAppMchSecret = mchSecret;
        }
        if (StringUtils.isEmpty(pubMchId)) {
            pubMchId = mchId;
        }
        if (StringUtils.isEmpty(pubMchSecret)) {
            pubMchSecret = mchSecret;
        }

    }

    public  void setAppId(String appId) {
        WxConfig.appId = appId;
    }

    public  void setAppSecret(String appSecret) {
        WxConfig.appSecret = appSecret;
    }

    public  void setAppMchId(String appMchId) {
        WxConfig.appMchId = appMchId;
    }

    public  void setAppMchSecret(String appMchSecret) {
        WxConfig.appMchSecret = appMchSecret;
    }

    public  void setMchId(String mchId) {
        WxConfig.mchId = mchId;
    }

    public  void setMchSecret(String mchSecret) {
        WxConfig.mchSecret = mchSecret;
    }

    public  void setWeAppId(String weAppId) {
        WxConfig.weAppId = weAppId;
    }

    public  void setWeAppSecret(String weAppSecret) {
        WxConfig.weAppSecret = weAppSecret;
    }

    public  void setWeAppMchId(String weAppMchId) {
        WxConfig.weAppMchId = weAppMchId;
    }

    public  void setWeAppMchSecret(String weAppMchSecret) {
        WxConfig.weAppMchSecret = weAppMchSecret;
    }

    public  void setPubAppId(String pubAppId) {
        WxConfig.pubAppId = pubAppId;
    }

    public  void setPubAppSecret(String pubAppSecret) {
        WxConfig.pubAppSecret = pubAppSecret;
    }

    public  void setPubMchId(String pubMchId) {
        WxConfig.pubMchId = pubMchId;
    }

    public  void setPubMchSecret(String pubMchSecret) {
        WxConfig.pubMchSecret = pubMchSecret;
    }
}
