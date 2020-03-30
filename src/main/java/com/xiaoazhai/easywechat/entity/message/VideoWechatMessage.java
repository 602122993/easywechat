package com.xiaoazhai.easywechat.entity.message;

import lombok.Data;

/**
 * @author zhai
 * @date 2020年3月30日21:30:49
 * 视频消息推送时间
 */
@Data
public class VideoWechatMessage extends BaseWechatMessage {

    /**
     * 视频消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String mediaId;
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String thumbMediaId;
    /**
     * 消息id
     */
    private String msgId;


}
