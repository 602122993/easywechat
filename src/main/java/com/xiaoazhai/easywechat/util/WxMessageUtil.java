package com.xiaoazhai.easywechat.util;

import com.xiaoazhai.easywechat.entity.request.WechatMessage;

/**
 * @author zhai
 * @date 2020年3月29日20:32:35
 * 微信消息工具类
 */
public class WxMessageUtil {


    public static WechatMessage castToWechatMessage(String message) {
        return XmlUtil.xmlToBean(message, true, WechatMessage.class, true);
    }


}
