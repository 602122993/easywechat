package com.xiaoazhai.easywechat.entity.message.respmsg;

import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpUtil;
import com.xiaoazhai.easywechat.util.WxMessageUtil;
import com.xiaoazhai.easywechat.util.XmlUtil;
import lombok.AllArgsConstructor;
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
public class ImageReturnWechatMessage extends ReturnWechatMessage {

    /**
     * 图片内部类
     */
    private Image image;
    /**
     * 图片文件内容,支持File String InputStream
     */
    private Object file;

    private String mediaId;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class Image implements ReturnMessageInterface {
        private String mediaId;
    }

    @Override
    public String getReturnMessage() {
        if (StringUtils.isEmpty(mediaId)) {
            if (file instanceof File) {
                mediaId = getMediaId((File) file);
            }
            if (file instanceof String) {
                mediaId = getMediaId((String) file);
            }
            if (file instanceof InputStream) {
                mediaId = getMediaId((InputStream) file);
            }
        }
        image = new Image(mediaId);
        doEmpty();

        return XmlUtil.beanToXml(this, true);
    }

    private void doEmpty() {
        file = null;
        mediaId = null;
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
