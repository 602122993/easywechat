package com.xiaoazhai.easywechat.entity.message;

import com.xiaoazhai.easywechat.enums.MsgTypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author zhai
 * @date 2020年3月29日20:58:36
 * 微信消息模板
 */
@Data
public class AllTypeWechatMessage extends BaseWechatMessage {


    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息id
     */
    private String msgId;
    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 图片消息媒体id
     */
    private String mediaId;
    /**
     * 语音格式
     */
    private String format;
    /**
     * 微信翻译后的结果
     */
    private String recognition;


}
