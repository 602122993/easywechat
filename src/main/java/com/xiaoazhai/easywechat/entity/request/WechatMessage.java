package com.xiaoazhai.easywechat.entity.request;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.xiaoazhai.easywechat.enums.MsgTypeEnum;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhai
 * @date 2020年3月29日20:58:36
 * 微信消息模板
 */
@Data
public class WechatMessage {

    /**
     * 开发者微信号
     */
    private String toUserName;
    /**
     * 发送方账号
     */
    private String fromUserName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 消息类型枚举
     */
    private MsgTypeEnum msgType;
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
