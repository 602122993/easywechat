package com.xiaoazhai.easywechat.entity.message;

import lombok.Data;

/**
 * @author zhai
 * @date 2020年3月30日21:16:05
 * 扫码事件消息
 */
@Data
public class ScanEventWechatMessage extends EventWechatMessage {
    /**
     * 扫码场景值
     */
    private String eventKey;
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String ticket;
}
