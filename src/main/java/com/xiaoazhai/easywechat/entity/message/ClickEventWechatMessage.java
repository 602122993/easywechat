package com.xiaoazhai.easywechat.entity.message;

import lombok.Data;

@Data
public class ClickEventWechatMessage extends EventWechatMessage {
    /**
     * 事件key值,与自定义菜单接口中KEY值对应
     */
    private String eventKey;



}
