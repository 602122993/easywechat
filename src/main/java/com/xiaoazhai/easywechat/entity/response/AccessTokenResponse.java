package com.xiaoazhai.easywechat.entity.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author zhai
 * @date 2020年2月23日18:50:36
 * <p>
 * accessToken返回结果
 * </p>
 */
@Data
public class AccessTokenResponse extends ErrorResponse {
    /**
     * 网页授权接口调用凭证
     */
    @JsonIgnore
    private String accessToken;

    private String expiresIn;
    @JsonIgnore
    private String refreshToken;

    private String openid;

    private Long timeStamp;


}
