package com.xiaoazhai.easywechat.entity.message;

import com.xiaoazhai.easywechat.enums.EventEnum;
import lombok.Data;

/**
 * @author zhai
 * @date 2020年3月30日21:45:48
 * 微信事件推送
 */
@Data
public class EventWechatMessage extends BaseWechatMessage {

    private EventEnum event;
}
