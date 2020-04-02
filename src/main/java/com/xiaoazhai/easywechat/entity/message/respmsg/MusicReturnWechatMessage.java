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
public class MusicReturnWechatMessage extends ReturnWechatMessage {

    /**
     * 语音内部类
     */
    private Music music;
    /**
     * 语音文件内容,支持File String InputStream
     */
    private Object file;
    /**
     * 视频标题
     */
    private String title;
    /**
     * 视频描述
     */
    private String description;
    /**
     * 音乐地址
     */
    private String musicUrl;
    /**
     * 高清音乐地址
     */
    private String HQMusicUrl;
    /**
     * 缩略图素材id
     */
    private String thumbMediaId;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class Music implements ReturnMessageInterface {
        /**
         * 视频标题
         */
        private String title;
        /**
         * 视频描述
         */
        private String description;
        /**
         * 音乐地址
         */
        private String musicUrl;
        /**
         * 高清音乐地址
         */
        private String HQMusicUrl;
        /**
         * 缩略图素材id
         */
        private String thumbMediaId;

    }

    @Override
    public String getReturnMessage() {
        if (StringUtils.isEmpty(thumbMediaId)) {
            thumbMediaId = getMediaId(thumbMediaId);
        }
        music = new Music(title, description, musicUrl, HQMusicUrl, thumbMediaId);
        doEmpty();
        return XmlUtil.beanToXml(this, true);
    }

    private void doEmpty() {
        file = null;
        thumbMediaId = null;
        title = null;
        description = null;
    }


}
