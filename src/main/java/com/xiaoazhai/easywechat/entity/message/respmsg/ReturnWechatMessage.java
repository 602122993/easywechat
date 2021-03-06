package com.xiaoazhai.easywechat.entity.message.respmsg;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpUtil;
import com.xiaoazhai.easywechat.config.WxConfig;
import com.xiaoazhai.easywechat.entity.message.BaseWechatMessage;
import com.xiaoazhai.easywechat.exception.AesException;
import com.xiaoazhai.easywechat.exception.WxPubException;
import com.xiaoazhai.easywechat.util.WxMessageUtil;
import com.xiaoazhai.easywechat.util.XmlUtil;
import lombok.Data;
import org.apache.coyote.http2.ByteUtil;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author zhai
 * @date 2020年3月30日22:04:20
 * 返回微信消息
 */
@Data
public abstract class ReturnWechatMessage extends BaseWechatMessage {

    public static ThreadLocal<BaseWechatMessage> threadLocal = new ThreadLocal<>();

    /**
     * 获取返回值消息
     *
     * @return
     */
    public abstract String getReturnMessage();

    public String getResult() throws AesException {
        if (WxConfig.aesSecret) {
            return XmlUtil.encode(getReturnMessage());
        }
        return XmlUtil.encode(getReturnMessage());
    }

    public void initMessage(BaseWechatMessage baseWechatMessage) {
        BaseWechatMessage message = threadLocal.get();
        baseWechatMessage.setToUserName(message.getFromUserName());
        baseWechatMessage.setFromUserName(message.getToUserName());
        baseWechatMessage.setCreateTime(System.currentTimeMillis() / 1000);
        threadLocal.remove();
    }


    public String getMediaId(Object file) {
        String mediaId = null;
        if (file instanceof File) {
            mediaId = getMediaId((File) file);
        }
        if (file instanceof String) {
            mediaId = getMediaId((String) file);
        }
        if (file instanceof InputStream) {
            mediaId = getMediaId((InputStream) file);
        }
        if (StringUtils.isEmpty(mediaId)) {
            throw new WxPubException("文件类型错误");
        }
        return mediaId;
    }

    private String getMediaId(File file) {
        try {
            return getMediaId(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getMediaId(String url) {
        return getMediaId(HttpUtil.createGet(url).execute().bodyStream());

    }

    private String getMediaId(InputStream inputStream) {

        return WxMessageUtil.uploadShoreTimeFile(inputStream, getMsgType());
    }


}
