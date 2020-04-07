package com.xiaoazhai.easywechat.entity.message.respmsg;

import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpUtil;
import com.xiaoazhai.easywechat.enums.MsgTypeEnum;
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
    public static class Image implements ReturnMessageInterface {
        private String mediaId;
    }

    @Override
    public String getReturnMessage() {
        setMsgType(MsgTypeEnum.image);
        if (StringUtils.isEmpty(mediaId)) {
            mediaId  = getMediaId(file);
        }
        image = new Image(mediaId);
        doEmpty();
        initMessage(this);
        return XmlUtil.beanToXml(this, true);
    }

    private void doEmpty() {
        file = null;
        mediaId = null;
    }




}
