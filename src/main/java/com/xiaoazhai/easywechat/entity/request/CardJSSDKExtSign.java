package com.xiaoazhai.easywechat.entity.request;

import cn.hutool.crypto.SecureUtil;
import com.xiaoazhai.easywechat.util.BeanUtil;
import com.xiaoazhai.easywechat.util.SignUtil;
import com.xiaoazhai.easywechat.util.WxPayUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author zhai
 * @date 2020年6月15日14:38:06
 * 发送微信卡券
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardJSSDKExtSign {

    /**
     * 签名apiticket
     */
    private String apiTicket;
    /**
     * 卡券id
     */
    private String cardId;

    /**
     * 指定发放code
     */
    private String code;
    /**
     * 指定openid发放
     */
    private String openid;
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 修正时间戳
     */
    private Long fixedBegintimestamp;
    /**
     * 领取渠道参数
     */
    private String outerStr;
    /**
     * 签名
     */
    private String signature;

    public CardJSSDKExtSign sign() {
        if (StringUtils.isEmpty(nonceStr)) {
            nonceStr = WxPayUtil.generateNonceStr();
        }
        Map<String, Object> paramMap = BeanUtil.beanToMap(this, true, true);
        String paramStr = SignUtil.sortByASCII(paramMap);
        this.signature = SecureUtil.sha1(paramStr);
        return this;
    }


}
