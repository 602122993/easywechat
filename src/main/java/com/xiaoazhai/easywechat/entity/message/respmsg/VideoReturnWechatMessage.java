package com.xiaoazhai.easywechat.entity.message.respmsg;

import com.xiaoazhai.easywechat.util.XmlUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * @author zhai
 * @date 2020年4月1日21:29:34
 * 返回图片素材
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoReturnWechatMessage extends ReturnWechatMessage {

    /**
     * 语音内部类
     */
    private Video video;
    /**
     * 语音文件内容,支持File String InputStream
     */
    private Object file;
    /**
     * 素材id
     */
    private String mediaId;
    /**
     * 视频标题
     */
    private String title;
    /**
     * 视频描述
     */
    private String description;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class Video implements ReturnMessageInterface {
        /**
         * 素材id
         */
        private String mediaId;
        /**
         * 视频标题
         */
        private String title;
        /**
         * 视频描述
         */
        private String description;

    }

    @Override
    public String getReturnMessage() {
        if (StringUtils.isEmpty(mediaId)) {
            mediaId  = getMediaId(file);
        }
        video = new Video(mediaId, title, description);
        doEmpty();
        return XmlUtil.beanToXml(this, true);
    }

    private void doEmpty() {
        file = null;
        mediaId = null;
        title = null;
        description = null;
    }


}
