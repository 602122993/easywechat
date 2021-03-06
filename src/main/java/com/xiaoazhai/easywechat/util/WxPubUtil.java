package com.xiaoazhai.easywechat.util;

import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xiaoazhai.easywechat.cache.CacheInterface;
import com.xiaoazhai.easywechat.config.WxConfig;
import com.xiaoazhai.easywechat.constants.WxConstants;
import com.xiaoazhai.easywechat.entity.request.*;
import com.xiaoazhai.easywechat.entity.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhai
 * @date 2020年2月23日18:42:31
 * <p>
 * 微信公众号开发工具
 * </p>
 */

@Component
public class WxPubUtil {


    @Autowired
    @Qualifier("easyWxCache")
    public void setEasyWxCache(CacheInterface easyWxCache) {
        WxPubUtil.easyWxCache = easyWxCache;
    }

    private static CacheInterface easyWxCache;


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

    public static String getWeappOpenIdByCode(String code) {
        AccessTokenRequest request = AccessTokenRequest.builder()
                .appid(WxConfig.weAppId)
                .secret(WxConfig.weAppSecret)
                .jsCode(code)
                .grantType("authorization_code")
                .build();
        return WxRequestUtil.get(WxConstants.JS_CODE, request, AccessTokenResponse.class).getOpenid();
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
    public static BaseResponse sendTemplateMessage(TemplateMessageRequest request, String accessToken) {
        request.setAccessToken(accessToken);
        String url = WxConstants.SEND_TEMPLATE_MESSAGE + "?access_token=" + request.getAccessToken();
        request.setAccessToken(null);
        return WxRequestUtil.post(url, request, BaseResponse.class);
    }

    public static BaseResponse sendTemplateMessage(TemplateMessageRequest request) {
        return sendTemplateMessage(request, getAccessToken().getAccessToken());
    }

    public static BaseResponse sendTemplateMessage(TemplateMessageRequest request, AccessTokenRequest accessTokenRequest) {
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
        if (easyWxCache.get(WxConstants.ACCESS_TOKEN_CACHE, request.getAppid(), AccessTokenResponse.class) == null) {
            request.setGrantType("client_credential");
            AccessTokenResponse response = WxRequestUtil.get(WxConstants.ACCESS_TOKEN, request, AccessTokenResponse.class);
            easyWxCache.set(WxConstants.ACCESS_TOKEN_CACHE, request.getAppid(), response, Integer.valueOf(response.getExpiresIn()) - 100);
        }
        return easyWxCache.get(WxConstants.ACCESS_TOKEN_CACHE, request.getAppid(), AccessTokenResponse.class);
    }


    public static String getWxCardTicket(String accessToken, String appid) {
        String ticket = easyWxCache.get(WxConstants.WX_CARD_TICKET, appid, String.class);
        if (ticket == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("access_token", accessToken);
            map.put("type", "wx_card");
            String response = HttpUtil.get(WxConstants.JS_TICKET, map);
            ticket = JSONUtil.parseObj(response).getStr("ticket");
            easyWxCache.set(WxConstants.WX_CARD_TICKET, appid, ticket, 7000);
        }
        return ticket;
    }

    public static String getWxCardTicket() {
        return getWxCardTicket(AccessTokenRequest.builder().appid(WxConfig.pubAppId).secret(WxConfig.pubAppSecret).build());
    }

    public static String getWxCardTicket(AccessTokenRequest request) {
        return getWxCardTicket(getAccessToken(request).getAccessToken(), request.getAppid());
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


    public static boolean createMenu(List<MenuInfo> menuInfoList) {
        return createMenu(menuInfoList, getAccessToken().getAccessToken());
    }

    public static boolean createMenu(List<MenuInfo> menuInfoList, String accessToken) {
        Map<String, Object> map = new HashMap<>();
        map.put("button", menuInfoList);
        WxRequestUtil.post(WxConstants.CREATE_MENU + "?access_token=" + accessToken, JSONUtil.toJsonStr(map), BaseResponse.class);
        return true;
    }

    public static boolean createMenu(List<MenuInfo> menuInfoList, AccessTokenRequest request) {
        AccessTokenResponse accessTokenResponse = getAccessToken(request);
        return createMenu(menuInfoList, accessTokenResponse.getAccessToken());
    }


    public static boolean createMenu(List<MenuInfo> menuInfoList, MatchRule matchRule) {
        return createMenu(menuInfoList, getAccessToken().getAccessToken(), matchRule);
    }

    public static boolean createMenu(List<MenuInfo> menuInfoList, String accessToken, MatchRule matchRule) {
        Map<String, Object> map = new HashMap<>();
        map.put("button", menuInfoList);
        map.put("matchrule", matchRule);
        WxRequestUtil.post(WxConstants.ADD_MENU_CONDITIONAL + "?access_token=" + accessToken, JSONUtil.toJsonStr(map), BaseResponse.class);
        return true;
    }

    public static boolean createMenu(List<MenuInfo> menuInfoList, AccessTokenRequest request, MatchRule matchRule) {
        AccessTokenResponse accessTokenResponse = getAccessToken(request);
        return createMenu(menuInfoList, accessTokenResponse.getAccessToken(), matchRule);
    }


    public static boolean deleteMenu() {
        return deleteMenu(getAccessToken().getAccessToken());
    }

    public static boolean deleteMenu(String accessToken) {
        WxRequestUtil.get(WxConstants.DELETE_MENU + "?access_token=" + accessToken, null, BaseResponse.class);
        return true;
    }

    public static boolean deleteMenu(AccessTokenRequest accessTokenRequest) {
        return deleteMenu(getAccessToken(accessTokenRequest).getAccessToken());
    }

    public static List<MenuInfoResponse> getMenu() {
        return getMenu(getAccessToken().getAccessToken());
    }

    public static List<MenuInfoResponse> getMenu(String accessToken) {
        List<MenuInfoResponse> menuInfoResponseList = new ArrayList<>();
        JSONObject jsonObject = WxRequestUtil.get(WxConstants.GET_MENU + "?access_token=" + accessToken, null);
        JSONObject menu = jsonObject.getJSONObject("menu");
        List<JSONObject> conditionalMenuList = jsonObject.getJSONArray("conditionalmenu").toList(JSONObject.class);
        List<MenuInfo> menuList = menu.getJSONArray("button").toList(MenuInfo.class);
        MenuInfoResponse menuResponse = new MenuInfoResponse();
        menuResponse.setMenuList(menuList);
        conditionalMenuList.forEach(conditionalMenu -> {
            MenuInfoResponse conditionalMenuResponse = new MenuInfoResponse();
            conditionalMenuResponse.setMatchRule(conditionalMenu.getBean("matchrule", MatchRule.class));
            conditionalMenuResponse.setMenuList(conditionalMenu.getJSONArray("button").toList(MenuInfo.class));
            menuInfoResponseList.add(conditionalMenuResponse);
        });
        return menuInfoResponseList;
    }

    public static List<MenuInfoResponse> getMenu(AccessTokenRequest accessTokenRequest) {
        return getMenu(getAccessToken(accessTokenRequest).getAccessToken());
    }


    public static String uploadImage(String imageUrl) {
        return uploadImage(HttpUtil.createGet(imageUrl).execute().bodyStream());
    }

    public static String uploadImage(File file) throws FileNotFoundException {
        return uploadImage(new FileInputStream(file));
    }

    public static String uploadImage(InputStream inputStream) {
        JSONObject response = JSONUtil.parseObj(HttpUtil.createPost(WxConstants.UPLOAD_IMAGE).form("buffer", IoUtil.readBytes(inputStream), "image.jpg")
                .form("access_token", WxPubUtil.getAccessToken().getAccessToken())
                .execute().body());
        System.out.println(response);
        return response.getStr("url");
    }

    public static CreateCardResponse createCard(Map<String, Object> param) {
        return createCard(param, getAccessToken().getAccessToken());
    }

    public static CreateCardResponse createCard(Map<String, Object> param, String accessToken) {
        return WxRequestUtil.post(WxConstants.CREATE_CARD + "?access_token=" + accessToken, param, CreateCardResponse.class);
    }

    public static CreateCardResponse createCard(Map<String, Object> param, AccessTokenRequest request) {
        return createCard(param, getAccessToken(request).getAccessToken());
    }

    public static BaseResponse importCode(String cardId, List<String> codeList, String accessToken) {
        Map<String, Object> param = new HashMap<>();
        param.put("card_id", cardId);
        param.put("code", codeList);
        return WxRequestUtil.post(WxConstants.IMPORT_CODE + "?access_token=" + accessToken, param, BaseResponse.class);
    }

    public static BaseResponse importCode(String cardId, List<String> codeList) {
        return importCode(cardId, codeList, getAccessToken().getAccessToken());
    }

    public static BaseResponse importCode(String cardId, List<String> codeList, AccessTokenRequest request) {
        return importCode(cardId, codeList, getAccessToken(request).getAccessToken());
    }

    public static BaseResponse getCodeCountByCardId(String cardId, String accessToken) {
        Map<String, Object> param = new HashMap<>();
        param.put("card_id", cardId);
        return WxRequestUtil.post(WxConstants.GET_CODE_COUNT_BY_CARD_ID, param, BaseResponse.class);
    }

    public static BaseResponse getCodeCountByCardId(String cardId, AccessTokenRequest request) {
        return getCodeCountByCardId(cardId, getAccessToken(request).getAccessToken());
    }

    public static BaseResponse getCodeCountByCardId(String cardId) {
        return getCodeCountByCardId(cardId, getAccessToken().getAccessToken());
    }

    public static BaseResponse checkCode(String cardId, List<String> codeList, String accessToken) {
        Map<String, Object> param = new HashMap<>();
        param.put("card_id", cardId);
        param.put("code", codeList);
        return WxRequestUtil.post(WxConstants.CHECK_CODE + "?access_token=" + accessToken, param, BaseResponse.class);
    }

    public static BaseResponse checkCode(String cardId, List<String> codeList) {
        return checkCode(cardId, codeList, getAccessToken().getAccessToken());
    }

    public static BaseResponse checkCode(String cardId, List<String> codeList, AccessTokenRequest request) {
        return checkCode(cardId, codeList, getAccessToken(request).getAccessToken());
    }

    public static SendMessageResponse sendMessageByOpenId(Map param, String accessToken) {
        return WxRequestUtil.post(WxConstants.SEND_MESSAGE + "?access_token=" + accessToken, param, SendMessageResponse.class);
    }

    public static SendMessageResponse sendMessageByOpenId(Map param) {
        return sendMessageByOpenId(param, getAccessToken().getAccessToken());
    }

    public static CreateCardQrCodeResponse createCardQrCode(String accessToken, CreateCardQRCodeRequest createCardQRCodeRequest) {
        Map<String, Object> paramMap = new HashMap<>();
        if (createCardQRCodeRequest.getCardList().size() == 1) {
            //创建单张卡券
            CreateCardQRCodeRequest.CardInfo cardInfo = createCardQRCodeRequest.getCardList().get(0);
            paramMap.put("action_name", "QR_CARD");
            paramMap.put("expire_seconds", createCardQRCodeRequest.getExpireSeconds());
            Map<String, Object> cardMap = new HashMap<>();
            Map<String, Object> actionInfo = new HashMap<>();
            cardMap.put("card_id", cardInfo.getCardId());
            cardMap.put("code", cardInfo.getCode());
            cardMap.put("openid", cardInfo.getOpenid());
            cardMap.put("is_unique_code", cardInfo.getIsUniqueCode());
            cardMap.put("outer_str", cardInfo.getOuterStr());
            actionInfo.put("card", cardMap);
            paramMap.put("action_info", actionInfo);
        } else {
            List<CreateCardQRCodeRequest.CardInfo> cardInfoList = new ArrayList<>();
            paramMap.put("action_name", "QR_MULTIPLE_CARD");
            Map<String, Object> multCard = new HashMap<>();
            Map<String, Object> actionInfo = new HashMap<>();
            List<Map<String, Object>> cardList = new ArrayList<>();
            paramMap.put("expire_seconds", createCardQRCodeRequest.getExpireSeconds());
            createCardQRCodeRequest.getCardList().forEach(card -> {
                Map<String, Object> cardMap = new HashMap<>();
                cardMap.put("card_id", card.getCardId());
                cardMap.put("code", card.getCode());
                cardMap.put("openid", card.getOpenid());
                cardMap.put("is_unique_code", card.getIsUniqueCode());
                cardMap.put("outer_str", card.getOuterStr());
                cardList.add(cardMap);
            });
            multCard.put("card_list", cardList);
            actionInfo.put("multiple_card", multCard);
            paramMap.put("action_info", actionInfo);
        }
        return WxRequestUtil.post(WxConstants.CREATE_CARD_QRCODE + "?access_token=" + accessToken, paramMap, CreateCardQrCodeResponse.class);
    }

    public static CreateCardLandingPageResponse createCardLandingPage(String accessToken, CreateCardLandingPageRequest createCardLandingPageRequest) {
        return WxRequestUtil.post(WxConstants.CREATE_LANDING_PAGE + "?access_token=" + accessToken, BeanUtil.beanToMap(createCardLandingPageRequest, false, true, true), CreateCardLandingPageResponse.class);
    }

    public static CardGetMpNewResponse getMpNewContent(String cardId) {
        return getMpNewContent(cardId, getAccessToken().getAccessToken());
    }

    public static CardGetMpNewResponse getMpNewContent(String cardId, String accessToken) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("card_id", cardId);
        return WxRequestUtil.post(WxConstants.GET_MP_NEW_CONTENT + "?access_token=" + accessToken, paramMap, CardGetMpNewResponse.class);
    }

    public static CardGetMpNewResponse getMpNewContent(String cardId, AccessTokenRequest accessTokenRequest) {
        return getMpNewContent(cardId, getAccessToken(accessTokenRequest).getAccessToken());
    }

    public static BaseResponse addKfAccount(String accessToken, KfAccountRequest kfAccountRequest) {
        return WxRequestUtil.post(WxConstants.ADD_KF_ACCOUNT + "?access_token=" + accessToken, BeanUtil.beanToMap(kfAccountRequest, false, true, true), BaseResponse.class);
    }


}
