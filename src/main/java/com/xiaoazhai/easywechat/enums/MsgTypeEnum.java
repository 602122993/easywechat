package com.xiaoazhai.easywechat.enums;

import com.xiaoazhai.easywechat.entity.message.*;

/**
 * @author zhai
 * @date 2020年3月29日21:08:50
 * 消息类型枚举
 */
public enum MsgTypeEnum {
    /**
     * 消息文本
     */
    text(TextWechatMessage.class),
    article(null),
    image(ImageWechatMessage.class),
    voice(VoiceWechatMessage.class),
    video(VideoWechatMessage.class),
    shortvideo(VideoWechatMessage.class),
    location(LocationWechatMessage.class),
    link(LinkWechatMessage.class),
    music(null),
    wxcard(null),
    event(EventWechatMessage.class);

    private Class<? extends BaseWechatMessage> clazz;

    MsgTypeEnum(Class<? extends BaseWechatMessage> message) {
        clazz = message;
    }

    public Class<? extends BaseWechatMessage> getClazz() {
        return clazz;
    }


}
