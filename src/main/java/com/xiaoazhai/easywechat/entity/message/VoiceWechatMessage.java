package com.xiaoazhai.easywechat.entity.message;

import lombok.Data;

/**
 * @author zhai
 * @date 2020年3月30日21:28:59
 * 语音消息推送
 */
@Data
public class VoiceWechatMessage extends BaseWechatMessage {
    /**
     * 语音消息媒体id
     */
    private String mediaId;

    /**
     * 语音格式
     */
    private String format;
    /**
     * 语音识别结果 Utf8编码
     */
    private String recognition;
    /**
     * 消息id
     */
    private String msgId;
}
