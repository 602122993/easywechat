package com.xiaoazhai.easywechat.entity.request;

import com.xiaoazhai.easywechat.entity.response.CreateCardQrCodeResponse;
import com.xiaoazhai.easywechat.util.WxPubUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhai
 * @date 2020年6月5日09:32:52
 * 查看微信卡券
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardQRCodeRequest {

    private Integer expireSeconds;

    private List<CardInfo> cardList;

    private String actionName;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CardInfo {

        private String cardId;

        private String code;

        private String openid;

        private Boolean isUniqueCode;

        private String outerStr;
    }


    public CreateCardQrCodeResponse execute() {
        return execute(WxPubUtil.getAccessToken().getAccessToken());
    }


    public CreateCardQrCodeResponse execute(AccessTokenRequest request) {
        return execute(WxPubUtil.getAccessToken(request).getAccessToken());
    }

    public CreateCardQrCodeResponse execute(String accessToken) {
        return WxPubUtil.createCardQrCode(accessToken, this);
    }
}
