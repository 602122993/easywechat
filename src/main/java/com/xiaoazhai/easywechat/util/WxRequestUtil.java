package com.xiaoazhai.easywechat.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xiaoazhai.easywechat.constants.WxConstants;
import com.xiaoazhai.easywechat.entity.request.WxPayRequest;
import com.xiaoazhai.easywechat.entity.request.WxPayToWalletRequest;
import com.xiaoazhai.easywechat.entity.request.WxRedPackageRequest;
import com.xiaoazhai.easywechat.entity.response.WxPayResponse;
import com.xiaoazhai.easywechat.entity.response.WxPayToWalletResponse;
import com.xiaoazhai.easywechat.entity.response.WxRedPackResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WxRequestUtil {


    public static WxRedPackResponse sendRedpack(WxRedPackageRequest request, InputStream inputStream) {
        String response = CertUtil.postData(WxConstants.SEND_REDPACK, XmlUtil.beanToXml(request), request.getMchId(), inputStream);
        log.info("微信支付返回结果-------------" + response);
        return XmlUtil.xmlToBean(response, true, WxRedPackResponse.class, true);
    }

    public static WxPayToWalletResponse sendToWallet(WxPayToWalletRequest request, InputStream inputStream) {
        String response = CertUtil.postData(WxConstants.PAY_TO_WALLET, XmlUtil.beanToXml(request), request.getMchid(), inputStream);
        log.info("微信支付返回结果-------------" + response);
        return XmlUtil.xmlToBean(response, true, WxPayToWalletResponse.class, true);
    }

    public static WxPayResponse payNormalRequest(WxPayRequest request) {
        String body = XmlUtil.beanToXml(request);
        String response = HttpUtil.post(WxConstants.UNIFIED_ORDER, body);
        log.info("微信支付返回结果-------------" + response);
        return XmlUtil.xmlToBean(response, true, WxPayResponse.class, true);
    }

    public static <T> T get(String url, Object requestBean, Class<T> clazz) {
        log.info("微信支付请求url-------------" + url);
        log.info("微信支付请求参数-------------" + JSONUtil.toJsonStr(requestBean));
        JSONObject response = JSONUtil.parseObj(HttpUtil.get(url, BeanUtil.beanToMap(requestBean, true, true)));
        Map<String, Object> result = new HashMap<>();
        response.forEach((key, value) -> result.put(XmlUtil.underlineToHump(key), value));
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(true);
        log.info("微信支付返回结果-------------" + response);
        return BeanUtil.mapToBean(result, clazz, copyOptions);
    }

    public static <T> T post(String url, Object requestBean, Class<T> resultClass) {
        log.info("微信支付请求url-------------" + url);
        log.info("微信支付请求参数-------------" + JSONUtil.toJsonStr(requestBean));
        JSONObject response = JSONUtil.parseObj(HttpUtil.post(url, JSONUtil.toJsonStr(BeanUtil.beanToMap(requestBean, true, true))));
        Map<String, Object> result = new HashMap<>();
        response.forEach((key, value) -> result.put(XmlUtil.underlineToHump(key), value));
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(true);
        log.info("微信支付返回结果-------------" + response);
        return BeanUtil.mapToBean(result, resultClass, copyOptions);
    }


    /***
     * 驼峰命名转为下划线命名
     *
     * @param para
     *        驼峰命名的字符串
     */

    public static String humpToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;
        if (!para.contains("_")) {
            for (int i = 0; i < para.length(); i++) {
                if (Character.isUpperCase(para.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return sb.toString().toLowerCase();
    }


}
