package com.xiaoazhai.easywechat.entity.response;

import lombok.Data;

/**
 * @author zhai
 * @date 2020年6月4日21:37:04
 * 发送消息返回结果
 */
@Data
public class SendMessageResponse extends BaseResponse {

    private String msgId;

    private String msgDataId;
}
