package com.xiaoazhai.easywechat.entity.message;

import lombok.Data;

/**
 * @author zhai
 * @date 2020年3月30日21:22:40
 * 点击菜单跳转连接事件
 */
@Data
public class ViewEventWechatMessage extends EventWechatMessage {
    /**
     * 事件key,跳转的url
     */
    private String eventKey;
}
