package com.xiaoazhai.easywechat.entity.message;

import lombok.Data;

/**
 * @author zhai
 * @date 2020年3月30日21:39:21
 * 链接消息
 */
@Data
public class LinkWechatMessage extends BaseWechatMessage {
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息描述
     */
    private String description;
    /**
     * 消息链接
     */
    private String url;
    /**
     * 消息id
     */
    private String msgId;
}
