package com.xiaoazhai.easywechat.entity.request;

import com.xiaoazhai.easywechat.entity.response.CreateCardResponse;
import com.xiaoazhai.easywechat.util.WxPubUtil;
import com.xiaoazhai.easywechat.util.WxRequestUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhai
 * @date 2020年6月3日20:11:59
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GrouponCardCreateRequest{

    /**
     * 团购专用,团购详情
     */
    private String dealDetail;

//    private Map generalMap() {
//        Map<String, Object> card = new HashMap<>();
//        Map<String, Object> param = new HashMap<>();
//        Map<String, Object> groupon = getBaseMap();
//        groupon.put("deal_detail", dealDetail);
//        card.put("card_type", "GROUPON");
//        card.put("groupon", groupon);
//        param.put("card", card);
//        return param;
//    }
//
//    public CreateCardResponse execute() {
//        return WxPubUtil.createCard(generalMap());
//    }
//
//    public CreateCardResponse execute(String token) {
//        return WxPubUtil.createCard(generalMap(), token);
//    }
//
//    public CreateCardResponse execute(AccessTokenRequest request) {
//        return WxPubUtil.createCard(generalMap(), request);
//    }
}
