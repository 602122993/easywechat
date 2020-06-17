package com.xiaoazhai.easywechat.entity.request;

import com.xiaoazhai.easywechat.entity.message.respmsg.ReturnMessageInterface;
import com.xiaoazhai.easywechat.entity.response.CreateCardLandingPageResponse;
import com.xiaoazhai.easywechat.enums.LandingPageSceneEnum;
import com.xiaoazhai.easywechat.util.WxPubUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * @author zhai
 * @date 2020年6月17日15:48:26
 * 创建卡券货架请求
 */
public class CreateCardLandingPageRequest {

    private String banner;

    private String pageTitle;

    private Boolean canShare;

    private LandingPageSceneEnum scene;

    private List<CardInfo> cardList;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CardInfo implements ReturnMessageInterface {

        private String cardId;

        private String thumbUrl;

    }


    public CreateCardLandingPageResponse execute() {
        return execute(WxPubUtil.getAccessToken().getAccessToken());
    }

    public CreateCardLandingPageResponse execute(AccessTokenRequest accessTokenRequest) {
        return execute(WxPubUtil.getAccessToken(accessTokenRequest).getAccessToken());
    }

    public CreateCardLandingPageResponse execute(String accessToken) {
        return WxPubUtil.createCardLandingPage(accessToken,this);
    }


}
