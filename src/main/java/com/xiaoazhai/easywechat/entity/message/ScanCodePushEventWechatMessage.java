package com.xiaoazhai.easywechat.entity.message;

import lombok.Data;

/**
 * @author zhai
 * @date 2020年5月8日21:11:21
 * <p>
 * 扫码推事件的事件推送
 * </p>
 */
@Data
public class ScanCodePushEventWechatMessage extends EventWechatMessage {

    private String eventKey;

    private ScanCodeInfo sendPicsInfo;

    @Data
    @InnerMessageClass
    public static class ScanCodeInfo {
        private String scanType;

        private String scanResult;
    }
}
