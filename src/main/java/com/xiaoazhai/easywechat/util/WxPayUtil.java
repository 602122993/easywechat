package com.xiaoazhai.easywechat.util;

import cn.hutool.core.net.NetUtil;
import cn.hutool.json.JSONUtil;
import com.xiaoazhai.easywechat.config.WxConfig;
import com.xiaoazhai.easywechat.entity.request.WxPayRequest;
import com.xiaoazhai.easywechat.entity.request.WxPayToWalletRequest;
import com.xiaoazhai.easywechat.entity.request.WxRedPackageRequest;
import com.xiaoazhai.easywechat.entity.response.WxPayResponse;
import com.xiaoazhai.easywechat.entity.response.WxPayToWalletResponse;
import com.xiaoazhai.easywechat.entity.response.WxRedPackResponse;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author zhai
 * @date 2020年2月23日11:19:15
 * <p>
 * 微信支付工具
 * </p>
 */
public class WxPayUtil {


    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new SecureRandom();

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static WxPayToWalletResponse sendToWallet(WxPayToWalletRequest request) {
        return sendToWallet(request, null);
    }
    public static WxPayToWalletResponse sendToWallet(WxPayToWalletRequest request,InputStream inputStream) {
        String mchSecret = request.getMchSecret();
        if (StringUtils.isEmpty(mchSecret)) {
            mchSecret = WxConfig.pubMchSecret;
        }
        if (StringUtils.isEmpty(request.getMchid())) {
            request.setMchid(WxConfig.pubMchId);
        }
        if (StringUtils.isEmpty(request.getMchAppid())) {
            request.setMchAppid(WxConfig.pubAppId);
        }
        request.setNonceStr(generateNonceStr());
        request.setSpbillCreateIp(NetUtil.getLocalhostStr());
        request.setMchSecret(null);
        request.setSign(SignUtil.getSign(request, mchSecret));
        return WxRequestUtil.sendToWallet(request, inputStream);
    }

    public static WxRedPackResponse sendRedPack(WxRedPackageRequest redPackageRequest, InputStream inputStream) {
        String mchSecret = redPackageRequest.getMchSecret();
        if (StringUtils.isEmpty(mchSecret)) {
            mchSecret = WxConfig.pubMchSecret;
        }
        if (StringUtils.isEmpty(redPackageRequest.getMchId())) {
            redPackageRequest.setMchId(WxConfig.pubMchId);
        }
        if (StringUtils.isEmpty(redPackageRequest.getWxappid())) {
            redPackageRequest.setWxappid(WxConfig.pubAppId);
        }
        redPackageRequest.setTotalNum(1);
        redPackageRequest.setNonceStr(generateNonceStr());
        redPackageRequest.setClientIp(NetUtil.getLocalhostStr());
        redPackageRequest.setMchSecret(null);
        redPackageRequest.setSign(SignUtil.getSign(redPackageRequest, mchSecret));
        return WxRequestUtil.sendRedpack(redPackageRequest, inputStream);
    }

    public static WxRedPackResponse sendRedPack(WxRedPackageRequest redPackageRequest) {
        return sendRedPack(redPackageRequest, null);
    }


    /**
     * 公众号统一request
     *
     * @param request
     * @return
     */
    public static WxPayResponse pubOrder(WxPayRequest request) {
        String mchSecret = request.getMchSecret();
        if (StringUtils.isEmpty(mchSecret)) {
            mchSecret = WxConfig.pubMchSecret;
        }
        if (StringUtils.isEmpty(request.getAppid())) {
            request.setAppid(WxConfig.pubAppId);
        }
        if (StringUtils.isEmpty(request.getMchId())) {
            request.setMchId(WxConfig.pubMchId);
        }
        request.setNonceStr(generateNonceStr());
        request.setMchSecret(null);
        request.setSign(SignUtil.getSign(request, mchSecret));
        validateBean(request);
        return WxRequestUtil.payNormalRequest(request);
    }

    /**
     * APP支付
     *
     * @param request
     * @return
     */
    public static WxPayResponse appOrder(WxPayRequest request) {
        String mchSecret = request.getMchSecret();
        if (StringUtils.isEmpty(mchSecret)) {
            mchSecret = WxConfig.appMchSecret;
        }
        if (StringUtils.isEmpty(request.getAppid())) {
            request.setAppid(WxConfig.appId);
        }
        if (StringUtils.isEmpty(request.getMchId())) {
            request.setMchId(WxConfig.appMchId);
        }
        request.setNonceStr(generateNonceStr());
        request.setMchSecret(null);
        request.setSign(SignUtil.getSign(request, mchSecret));
        return WxRequestUtil.payNormalRequest(request);
    }

    /**
     * 校验bean
     *
     * @param bean
     * @param groups
     * @param <T>
     */
    public static <T> void validateBean(T bean, Class<?>... groups) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(bean, groups);
        if (constraintViolations.isEmpty()) {
            System.out.println("校验通过");
            return;
        }
        List<String> errors = new ArrayList<>(10);
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            errors.add(constraintViolation.getPropertyPath() + constraintViolation.getMessage());
        }
        throw new ValidationException(JSONUtil.toJsonStr(errors));
    }

    public static String generateNonceStr() {
        char[] nonceChars = new char[32];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }

}
