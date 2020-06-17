package com.xiaoazhai.easywechat.entity.request;

import com.xiaoazhai.easywechat.entity.response.BaseResponse;
import com.xiaoazhai.easywechat.util.WxPubUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * @author zhai
 * @date 2020年6月17日21:52:10
 * 创建客服账号请求
 */
public class KfAccountRequest {

    private String kfAccount;

    private String nickname;

    private String password;

    private String kfNick;

    private String kfId;

    private String media;

    public BaseResponse add() {
        return add(WxPubUtil.getAccessToken().getAccessToken());
    }

    public BaseResponse add(AccessTokenRequest request) {
        return add(WxPubUtil.getAccessToken(request).getAccessToken());
    }

    public BaseResponse add(String accessToken) {
        return WxPubUtil.addKfAccount(accessToken, this);
    }

}
