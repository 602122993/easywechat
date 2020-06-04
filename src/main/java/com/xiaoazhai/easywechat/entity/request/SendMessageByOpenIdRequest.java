package com.xiaoazhai.easywechat.entity.request;

import com.xiaoazhai.easywechat.entity.response.SendMessageResponse;
import com.xiaoazhai.easywechat.enums.MsgTypeEnum;
import com.xiaoazhai.easywechat.enums.SendMsgTypeEnum;
import com.xiaoazhai.easywechat.util.WxPubUtil;
import com.xiaoazhai.easywechat.util.WxRequestUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhai
 * @date 2020年6月4日21:28:43
 * 根据openid群发卡券
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendMessageByOpenIdRequest {

    private List<String> touser;

    private SendMsgTypeEnum msgtype;

    private String cardId;


    public SendMessageResponse execute() {
        return WxPubUtil.sendMessageByOpenId(getParam());
    }

    private Map getParam() {
        Map<String, Object> map = new HashMap<>();
        map.put("touser", touser);
        map.put("msgtype", msgtype.toString());
        switch (msgtype) {
            case wxcard:
                Map<String, Object> wxcard = new HashMap<>();
                wxcard.put("card_id", cardId);
                map.put("wxcard", wxcard);
                break;
        }
        return map;
    }

}
