package com.xiaoazhai.easywechat.entity.request;

import com.xiaoazhai.easywechat.entity.response.WxRedPackResponse;
import com.xiaoazhai.easywechat.util.WxPayUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * @author zhai
 * @date 2020年2月28日13:10:18
 * 微信红包请求
 *
 */
public class WxRedPackageRequest {

    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;
    /**
     * 商户订单号
     */
    private String mchBillno;
    /**
     * 商户id
     */
    private String mchId;
    /**
     * 微信公众号appid
     */
    private String wxappid;
    /**
     * 商户名称
     */
    private String sendName;
    /**
     * 用户openid
     */
    private String reOpenid;
    /**
     * 付款总金额 分
     */
    private Integer totalAmount;
    /**
     * 红包发放总人数
     */
    private Integer totalNum;
    /**
     * 祝福语
     */
    private String wishing;
    /**
     * ip地址
     */
    private String clientIp;
    /**
     * 活动名称
     */
    private String actName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 场景id
     */
    private String sceneId;

    /**
     * 活动信息
     */
    private String riskInfo;

    private String mchSecret;

    public WxRedPackResponse execute(InputStream inputStream) {
        return WxPayUtil.sendRedPack(this, inputStream);
    }

    public WxRedPackResponse execute() {
        return WxPayUtil.sendRedPack(this);
    }

}


