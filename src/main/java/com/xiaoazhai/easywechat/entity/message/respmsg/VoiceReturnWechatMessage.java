package com.xiaoazhai.easywechat.entity.message.respmsg;

import cn.hutool.http.HttpUtil;
import com.xiaoazhai.easywechat.util.WxMessageUtil;
import com.xiaoazhai.easywechat.util.XmlUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author zhai
 * @date 2020年4月1日21:29:34
 * 返回图片素材
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoiceReturnWechatMessage extends ReturnWechatMessage {

    /**
     * 语音内部类
     */
    private Voice voice;
    /**
     * 语音文件内容,支持File String InputStream
     */
    private Object file;

    private String mediaId;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class Voice implements ReturnMessageInterface {
        private String mediaId;
    }

    @Override
    public String getReturnMessage() {
        if (StringUtils.isEmpty(mediaId)) {
            mediaId  = getMediaId(file);
        }
        voice = new Voice(mediaId);
        doEmpty();
        return XmlUtil.beanToXml(this, true);
    }

    private void doEmpty() {
        file = null;
        mediaId = null;
    }


}
