package com.xiaoazhai.easywechat.entity.message;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.xiaoazhai.easywechat.enums.MsgTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    private Long createTime;
    /**
     * 消息类型枚举
     */
    private MsgTypeEnum msgType;


}
