package com.xiaoazhai.easywechat.entity.request;

import com.xiaoazhai.easywechat.entity.response.WxPayResponse;
import com.xiaoazhai.easywechat.enums.WxPayChannelEnum;
import com.xiaoazhai.easywechat.util.WxPayUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * @author zhai
 * @date 2020年2月21日13:18:32
 * <p>
 * 微信支付请求体
 * </p>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WxPayRequest {
    /**
     * 微信的appId
     */
    @NotNull
    private String appid;

    /**
     * 微信商户号
     */
    @NotNull
    private String mchId;
    /**
     * 设备号
     */
    private String deviceInfo;
    /**
     * 随机字符串
     */
    @NotNull
    private String nonceStr;
    /**
     * 签名
     */
    @NotNull
    private String sign;
    /**
     * 签名类型
     */
    private String signType;
    /**
     * 商品描述
     */
    @NotNull
    private String body;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 附加信息
     */
    private String attach;
    /**
     * 商户订单号
     */
    @NotNull
    private String outTradeNo;
    /**
     * 货币类型
     */
    private String feeType;
    /**
     * 总金额
     */
    @NotNull
    private Integer totalFee;
    /**
     * 终端ip
     */
    @NotNull
    private String spbillCreateIp;
    /**
     * 交易起始时间
     */
    private String timeStart;
    /**
     * 交易结束时间
     */
    private String timeExpire;
    /**
     * 订单优惠标记
     */
    private String goodsTag;
    /**
     * 通知地址
     */
    @NotNull
    private String notifyUrl;
    /**
     * 交易类型
     */
    @NotNull
    private WxPayChannelEnum tradeType;
    /**
     * 制定支付方式
     */
    private String limitPay;
    /**
     * 开发票入口开放标识
     */
    private String receipt;
    /**
     * 公开id
     */
    private String openid;
    /**
     * 场景信息
     */
    private String sceneInfo;

    private String mchSecret;

    public WxPayResponse execute() {
        if (!StringUtils.isEmpty(this.getTradeType())) {
            switch (this.getTradeType()) {
                case APP:
                    return WxPayUtil.appOrder(this);
                case WE_APP:
                    return null;
                case NATIVE:
                case JSAPI:
                case MWEB:
                    return WxPayUtil.pubOrder(this);
            }
        }
        //TODO   抛出异常
        return null;
    }
}
