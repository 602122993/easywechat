package com.xiaoazhai.easywechat.entity.message;

import com.xiaoazhai.easywechat.enums.MsgTypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author zhai
 * @date 2020年3月30日18:50:18
 * 微信消息顶级类
 */
@Data
public class BaseWechatMessage {
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
}
