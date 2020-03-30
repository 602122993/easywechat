package com.xiaoazhai.easywechat.entity.message;

import lombok.Data;

/**
 * @author zhai
 * @date 2020年3月30日21:18:30
 * 上报地理位置事件
 */
@Data
public class LocationEventWechatMessage extends EventWechatMessage {

    /**
     * 地理位置纬度
     */
    private Double latitude;
    /**
     * 地理位置经度
     */
    private Double longitude;
    /**
     * 地理位置精度
     */
    private Double precision;

}
