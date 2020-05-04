package com.xiaoazhai.easywechat.util;

import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReUtil;
import com.xiaoazhai.easywechat.config.WxConfig;
import com.xiaoazhai.easywechat.entity.message.ImageWechatMessage;
import com.xiaoazhai.easywechat.entity.message.respmsg.ImageReturnWechatMessage;
import com.xiaoazhai.easywechat.entity.message.respmsg.TextReturnWechatMessage;
import com.xiaoazhai.easywechat.entity.request.CheckInfo;
import com.xiaoazhai.easywechat.exception.AesException;
import com.xiaoazhai.easywechat.util.aesutil.WXBizMsgCrypt;
import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class XmlUtil extends cn.hutool.core.util.XmlUtil {


    public static String mapToXml(Map<String, Object> data) {
        return mapToXmlStr(data, "xml");
    }

    public static <T> T xmlToBean(String xml, boolean isToHump, Class<T> clazz, boolean ignoreNull) {
        Map<String, Object> map = xmlToMap(xml);
        Map<String, Object> result = new HashMap<>();
        if (isToHump) {
            map.forEach((key, value) -> {
                if ("CreateTime".equals(key)) {
                    value = Long.valueOf(value.toString()) / 1000;
                }
                if (key.length() > 0 && Character.isUpperCase(key.charAt(0))) {
                    result.put(key.replace(key.charAt(0), Character.toLowerCase(key.charAt(0))), value);
                } else {
                    result.put(underlineToHump(key), value);
                }
            });
        }
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(ignoreNull);
        return BeanUtil.mapToBean(result, clazz, copyOptions);
    }

    public static <T> String beanToXml(T bean, boolean isToFirstUpperCase) {

        String result = "";
        try {
            result = mapToXml(BeanUtil.beanToMap(bean, isToFirstUpperCase, false, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReUtil.delFirst("\\<\\?.*\\?\\>", result);
    }

    public static <T> String beanToXml(T bean) {
        return beanToXml(bean, false);
    }

    /**
     * 下划线转驼峰
     *
     * @param para
     * @return
     */
    public static String underlineToHump(String para) {
        StringBuilder result = new StringBuilder();
        String a[] = para.split("_");
        for (String s : a) {
            if (!para.contains("_")) {
                result.append(s);
                continue;
            }
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    public static String decode(CheckInfo checkInfo) throws ParserConfigurationException, IOException, SAXException, AesException {
        WXBizMsgCrypt pc = new WXBizMsgCrypt(WxConfig.pubToken, WxConfig.aesKey, WxConfig.pubAppId);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        dbf.setXIncludeAware(false);
        dbf.setExpandEntityReferences(false);

        DocumentBuilder db = dbf.newDocumentBuilder();
        Map<String, Object> map = xmlToMap(checkInfo.getPostData());
        String encrypt = map.get("Encrypt").toString();
        String msgSignature = checkInfo.getMsgSignature();

        String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
        String fromXML = String.format(format, encrypt);

        //
        // 公众平台发送消息给第三方，第三方处理
        //

        // 第三方收到公众号平台发送的消息
        return pc.decryptMsg(msgSignature, checkInfo.getTimestamp(), checkInfo.getNonce(), fromXML);

    }

    public static String encode(String result) throws AesException {
        WXBizMsgCrypt pc = new WXBizMsgCrypt(WxConfig.pubToken, WxConfig.aesKey, WxConfig.pubAppId);
        Long timestamp = System.currentTimeMillis() / 1000;
        String nonceStr = IdUtil.fastSimpleUUID();
        return pc.encryptMsg(result, timestamp.toString(), nonceStr);
    }

    public static void main(String[] args) {
        try {
            new WXBizMsgCrypt("azhai", "RHlCxkFd88jNN7vTh7CnjI19Xea8Je38eZdqLx6aiO3", "asdf");
        } catch (AesException e) {
            e.printStackTrace();
        }
        Base64.decodeBase64("RHlCxkFd88jNN7vTh7CnjI19Xea8Je38eZdqLx6aiO3=");
    }
}

