package com.xiaoazhai.easywechat.entity.request;

import cn.hutool.core.util.StrUtil;
import com.xiaoazhai.easywechat.entity.response.BaseResponse;
import com.xiaoazhai.easywechat.util.WxPubUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhai
 * @date 2020年3月3日14:15:42
 * 发送模板消息请求
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplateMessageRequest {

    private String accessToken;

    private String touser;

    private String templateId;

    private String url;


    private DataTemplate data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataTemplate {
        private DataValue first;
        private DataValue keyword1;
        private DataValue keyword2;
        private DataValue keyword3;
        private DataValue keyword4;
        private DataValue keyword5;
        private DataValue remark;

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataValue {
        private String value;

        private String color;

    }

    public BaseResponse execute() {
        if (StrUtil.isNotEmpty(accessToken)) {
            return WxPubUtil.sendTemplateMessage(this, accessToken);
        }
        return WxPubUtil.sendTemplateMessage(this);
    }

    public BaseResponse execute(AccessTokenRequest request) {
        return WxPubUtil.sendTemplateMessage(this, request);
    }
}
