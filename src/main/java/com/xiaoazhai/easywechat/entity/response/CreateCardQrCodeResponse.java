package com.xiaoazhai.easywechat.entity.response;

import lombok.Data;

@Data
/**
 * @author zhai
 * @date 2020年6月17日14:30:16
 * 创建卡券二维码返回结果
 */
public class CreateCardQrCodeResponse extends BaseResponse {

    private String ticket;

    private Integer expireSeconds;

    private String url;

    private String showQrcodeUrl;

}
