package com.xiaoazhai.easywechat.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.xiaoazhai.easywechat.config.WxConfig;
import com.xiaoazhai.easywechat.constants.WxConstants;
import com.xiaoazhai.easywechat.entity.request.AccessTokenRequest;
import com.xiaoazhai.easywechat.entity.request.TemplateMessageRequest;
import com.xiaoazhai.easywechat.entity.request.WxUserInfoRequest;
import com.xiaoazhai.easywechat.entity.response.AccessTokenResponse;
import com.xiaoazhai.easywechat.entity.response.ErrorResponse;
import com.xiaoazhai.easywechat.entity.response.JSSDKResponse;
import com.xiaoazhai.easywechat.entity.response.WxUserInfoResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhai
 * @date 2020年2月23日18:42:31
 * <p>
 * 微信公众号开发工具
 * </p>
 */
public class WxPubUtil {


    private static final Map<String, AccessTokenResponse> ACCESS_TOKEN_MAP = new ConcurrentHashMap<>();


    public static WxUserInfoResponse getWxPubUserInfoByOpenId(String openId) {
        return getWxPubUserInfoByOpenId(openId, getAccessToken().getAccessToken());
    }

    public static WxUserInfoResponse getWxPubUserInfoByOpenId(String openId, String accessToken) {
        WxUserInfoRequest request = WxUserInfoRequest.builder().accessToken(accessToken).openid(openId).build();
        return WxRequestUtil.get(WxConstants.USE_INFO, request, WxUserInfoResponse.class);
    }

    public static WxUserInfoResponse getWxPubUserInfoByOpenId(String openId, AccessTokenRequest request) {
        return getWxPubUserInfoByOpenId(openId, getAccessToken(request).getAccessToken());
    }

    public static List<WxUserInfoResponse> getWxPubUserInfoByOpenId(List<String> openIdList) {
        return getWxPubUserInfoByOpenId(openIdList, getAccessToken().getAccessToken());
    }

    public static List<WxUserInfoResponse> getWxPubUserInfoByOpenId(List<String> openIdList, String accessToken) {
        List<WxUserInfoRequest> list = new ArrayList<>();
        openIdList.forEach(openid -> list.add(WxUserInfoRequest.builder().openid(openid).build()));
        Map map = new HashMap();
        map.put("user_list", list);
        return WxRequestUtil.post(WxConstants.USE_INFO, map, WxUserInfoResponse.class).getUserInfoList();
    }

    public static List<WxUserInfoResponse> getWxPubUserInfoByOpenId(List<String> openIdList, AccessTokenRequest request) {
        return getWxPubUserInfoByOpenId(openIdList, getAccessToken(request).getAccessToken());
    }

    /**
     * 发送模板消息
     *
     * @param request
     * @param accessToken
     * @return
     */
    public static ErrorResponse sendTemplateMessage(TemplateMessageRequest request, String accessToken) {
        request.setAccessToken(accessToken);
        String url = WxConstants.SEND_TEMPLATE_MESSAGE + "?access_token=" + request.getAccessToken();
        request.setAccessToken(null);
        return WxRequestUtil.post(url, request, ErrorResponse.class);
    }

    public static ErrorResponse sendTemplateMessage(TemplateMessageRequest request) {
        return sendTemplateMessage(request, getAccessToken().getAccessToken());
    }

    public static ErrorResponse sendTemplateMessage(TemplateMessageRequest request, AccessTokenRequest accessTokenRequest) {
        return sendTemplateMessage(request, getAccessToken(accessTokenRequest).getAccessToken());
    }

    /**
     * 获取openid
     *
     * @param code 微信方面获取的code
     * @return
     */
    public static AccessTokenResponse getOpenIdByCode(String code) {
        AccessTokenRequest request = AccessTokenRequest.builder()
                .appid(WxConfig.pubAppId)
                .secret(WxConfig.pubAppSecret)
                .code(code)
                .grantType("authorization_code")
                .build();
        return WxRequestUtil.get(WxConstants.AUTH_URL, request, AccessTokenResponse.class);
    }

    /**
     * 根据用户code获取用户信息
     *
     * @param code
     * @return
     */
    public static WxUserInfoResponse getWxUserInfoByCode(String code) {
        AccessTokenResponse response = getOpenIdByCode(code);
        CopyOptions options = new CopyOptions();
        options.setIgnoreNullValue(true);
        WxUserInfoRequest request = new WxUserInfoRequest();
        BeanUtil.copyProperties(response, request, options);
        return WxRequestUtil.get(WxConstants.USER_INFO, request, WxUserInfoResponse.class);
    }

    /**
     * 获取accessTOken
     *
     * @return
     */
    public static AccessTokenResponse getAccessToken() {
        return getAccessToken(AccessTokenRequest.builder()
                .appid(WxConfig.pubAppId)
                .secret(WxConfig.pubAppSecret).build());
    }

    /**
     * 根据已有的appid获取accesstoken
     *
     * @param request
     * @return
     */
    public static AccessTokenResponse getAccessToken(AccessTokenRequest request) {
        if (ACCESS_TOKEN_MAP.get(request.getAppid()) == null || ACCESS_TOKEN_MAP.get(request.getAppid()).getTimeStamp() < System.currentTimeMillis()) {
            request.setGrantType("client_credential");
            AccessTokenResponse response = WxRequestUtil.get(WxConstants.ACCESS_TOKEN, request, AccessTokenResponse.class);
            response.setTimeStamp(System.currentTimeMillis() + (1000 * Integer.valueOf(response.getExpiresIn()) - 200));
            ACCESS_TOKEN_MAP.put(request.getAppid(), response);
        }
        return ACCESS_TOKEN_MAP.get(request.getAppid());
    }

    public static JSSDKResponse getJsSign(String url) {
        return getJsSign(AccessTokenRequest.builder().appid(WxConfig.pubAppId).secret(WxConfig.pubAppSecret).build(), url);
    }

    public static JSSDKResponse getJsSign(String accessToken, String url) {
        url = URLUtil.decode(url);
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", accessToken);
        map.put("type", "jsapi");
        String response = HttpUtil.get(WxConstants.JS_TICKET, map);
        String ticket = JSONUtil.parseObj(response).getStr("ticket");
        String noncestr = WxPayUtil.generateNonceStr();
        Long timestamp = System.currentTimeMillis();
        map.clear();
        map.put("jsapi_ticket", ticket);
        map.put("noncestr", noncestr);
        map.put("timestamp", timestamp);
        map.put("url", url);
        String sign = SecureUtil.sha1(SignUtil.sortByASCII(map));
        JSSDKResponse jssdkResponse = new JSSDKResponse();
        jssdkResponse.setNoncestr(noncestr);
        jssdkResponse.setTimestamp(timestamp);
        jssdkResponse.setSign(sign);
        jssdkResponse.setAppid(WxConfig.pubAppId);
        return jssdkResponse;
    }

    public static JSSDKResponse getJsSign(AccessTokenRequest request, String url) {
        AccessTokenResponse accessTokenResponse = getAccessToken(request);
        return getJsSign(accessTokenResponse.getAccessToken(), url);
    }

}
