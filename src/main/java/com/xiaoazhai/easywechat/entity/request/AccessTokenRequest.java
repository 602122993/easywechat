package com.xiaoazhai.easywechat.entity.request;

import com.xiaoazhai.easywechat.entity.response.AccessTokenResponse;
import com.xiaoazhai.easywechat.util.WxPubUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhai
 * @date 2020年2月23日20:18:20
 * 获取 基础信息相关请求
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenRequest {
    /**
     * appid
     */
    private String appid;
    /**
     * appSecret
     */
    private String secret;
    /**
     * 用户code
     */
    private String code;
    /**
     * 请求方式
     */
    private String grantType;





}
