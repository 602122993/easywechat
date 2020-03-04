package com.xiaoazhai.easywechat.entity.request;

import com.xiaoazhai.easywechat.entity.response.WxPayToWalletResponse;
import com.xiaoazhai.easywechat.enums.CheckNameEnum;
import com.xiaoazhai.easywechat.util.WxPayUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

/**
 * @author zhai
 * @date 2020年3月3日13:25:26
 * 微信付款到零钱请求
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WxPayToWalletRequest {

    private String mchSecret;
    /**
     * 商户账号appid
     */
    private String mchAppid;
    /**
     * 商户id
     */
    private String mchid;
    /**
     * 设备号
     */
    private String deviceInfo;
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
    private String partnerTradeNo;
    /**
     * 用户openid
     */
    private String openid;
    /**
     * 校验用户姓名选项
     */
    private CheckNameEnum checkName;
    /**
     * 收款用户姓名
     */
    private String reUserName;
    /**
     * 金额
     */
    private Integer amount;
    /**
     * 企业付款备注
     */
    private String desc;
    /**
     * ip地址
     */
    private String spbillCreateIp;


    public WxPayToWalletResponse execute() {
        return WxPayUtil.sendToWallet(this);
    }

    public WxPayToWalletResponse execute(InputStream inputStream) {
        return WxPayUtil.sendToWallet(this,inputStream);
    }
}
