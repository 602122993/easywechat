package com.xiaoazhai.easywechat.entity.message;

import lombok.Data;

@Data
public class SubscribeEventWechatMessage extends EventWechatMessage {
    /**
     * 关注事件场景值
     */
    private String eventKey;
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String ticket;
}
