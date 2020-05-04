package com.xiaoazhai.easywechat.util;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xiaoazhai.easywechat.config.WxConfig;
import com.xiaoazhai.easywechat.constants.WxConstants;
import com.xiaoazhai.easywechat.entity.message.BaseWechatMessage;
import com.xiaoazhai.easywechat.entity.message.respmsg.ReturnWechatMessage;
import com.xiaoazhai.easywechat.entity.request.AccessTokenRequest;
import com.xiaoazhai.easywechat.entity.request.WxPayRequest;
import com.xiaoazhai.easywechat.enums.MsgTypeEnum;
import com.xiaoazhai.easywechat.exception.WxPubException;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhai
 * @date 2020年3月29日20:32:35
 * 微信消息工具类
 */
public class WxMessageUtil {


    public static BaseWechatMessage castToWechatMessage(String message, Class<? extends BaseWechatMessage> clazz) {
        return XmlUtil.xmlToBean(message, true, clazz, true);
    }


    public static String uploadShoreTimeFile(InputStream inputStream, MsgTypeEnum typeEnum) {
//        String fileType = FileTypeUtil.getType(inputStream);
        JSONObject response = JSONUtil.parseObj(HttpUtil.createPost(WxConstants.UPLOAD_SHORE_TIME_SOURCE).form("media", IoUtil.readBytes(inputStream), "file.jpg")
                .form("access_token", WxPubUtil.getAccessToken().getAccessToken())
                .form("type", typeEnum.toString())
                .execute().body());
        if (StringUtils.isEmpty(response.get("errcode"))) {
            return response.get("media_id").toString();
        }
        throw new WxPubException(response.get("errmsg").toString());
    }


}
