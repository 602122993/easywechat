package com.xiaoazhai.easywechat.entity.message;

import lombok.Data;

/**
 * @author zhai
 * @date 2020年3月30日18:53:16
 * 文本消息处理
 */
@Data
public class TextWechatMessage extends BaseWechatMessage {

    /**
     * 文本内容
     */
    private String content;
    /**
     * 消息id
     */
    private String msgId;

}
