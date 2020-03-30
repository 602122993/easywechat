package com.xiaoazhai.easywechat.entity.message;

import lombok.Data;

/**
 * @author zhai
 * @date 2020年3月30日21:25:57
 * 图片消息
 */
@Data
public class ImageWechatMessage extends BaseWechatMessage {

    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 图片消息媒体id,可以调用获取临时素材接口拉取数据
     */
    private String mediaId;
    /**
     * 消息id
     */
    private String msgId;

}
