package com.xiaoazhai.easywechat.util;

import com.xiaoazhai.easywechat.entity.message.AllTypeWechatMessage;
import com.xiaoazhai.easywechat.entity.message.BaseWechatMessage;

/**
 * @author zhai
 * @date 2020年3月29日20:32:35
 * 微信消息工具类
 */
public class WxMessageUtil {


    public static BaseWechatMessage castToWechatMessage(String message, Class<? extends BaseWechatMessage> clazz) {
        return XmlUtil.xmlToBean(message, true,clazz, true);
    }


}
