package com.xiaoazhai.easywechat.entity.message;

import lombok.Data;

/**
 * @author zhai
 * @date 2020年3月30日21:37:54
 * 地理位置
 */
@Data
public class LocationWechatMessage extends BaseWechatMessage {
    /**
     * 地理位置纬度
     */
    private String location_X;
    /**
     * 地理位置精度
     */
    private String location_Y;
    /**
     * 地图缩放大小
     */
    private String scale;
    /**
     * 地理位置信息
     */
    private String label;
    /**
     * 消息id
     */
    private String msgId;
}
