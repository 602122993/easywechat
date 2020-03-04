package com.xiaoazhai.easywechat.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhai
 * @date 2020年2月23日19:43:14
 * <p>
 * 微信获取用户信息请求request
 * </p>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WxUserInfoRequest {
    /**
     * 网页授权接口调用凭证
     */
    private String accessToken;
    /**
     * 用户的唯一标识
     */
    private String openid;
    /**
     * 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
     */
    private String lang;
}
