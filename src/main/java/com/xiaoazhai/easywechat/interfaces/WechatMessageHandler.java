package com.xiaoazhai.easywechat.interfaces;

import com.xiaoazhai.easywechat.entity.message.BaseWechatMessage;

/**
 * @author zhai
 * @date 2020年3月30日14:49:39
 * 微信消息处理handler
 */
public interface WechatMessageHandler<T> {

    /**
     * 消息发送的处理接口
     *
     * @param message
     */
    void onMessage(BaseWechatMessage message);

}
