package com.xiaoazhai.easywechat.aspect;


import cn.hutool.json.JSONUtil;
import com.xiaoazhai.easywechat.entity.message.BaseWechatMessage;
import com.xiaoazhai.easywechat.entity.message.VideoWechatMessage;
import com.xiaoazhai.easywechat.entity.message.respmsg.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param <T>
 * @author zhai
 * @date 2020年3月30日17:53:31
 * 消息处理器注册父类
 */
public abstract class AbstractMessageHandler<T extends BaseWechatMessage> {
    /**
     * 消息处理器注册存储容器
     */
    public final static Map<String, AbstractMessageHandler> registerMap = new HashMap<>();

    public final static String SUCCESS = "success";
    public final static String CONTINUE = "success";

    /**
     * 消息处理
     *
     * @param message
     * @return obj
     */
    public abstract Object onMessage(T message);

    /**
     * 返回文本消息
     *
     * @param message 文本消息内容
     * @return
     */
    public ReturnWechatMessage textMessage(String message) {
        return TextReturnWechatMessage.builder().content(message).build();
    }

    /**
     * 返回图片消息
     *
     * @param imageUrl 图片地址
     * @return
     */
    public ReturnWechatMessage imageMessage(String imageUrl) {
        return ImageReturnWechatMessage.builder().file(imageUrl).build();
    }

    /**
     * 返回图片消息
     *
     * @param inputStream 图片流
     * @return
     */
    public ReturnWechatMessage imageMessage(InputStream inputStream) {
        return ImageReturnWechatMessage.builder().file(inputStream).build();
    }

    /**
     * 返回图片消息
     *
     * @param file 图片文件
     * @return
     */
    public ReturnWechatMessage imageMessage(File file) {
        return ImageReturnWechatMessage.builder().file(file).build();
    }

    /**
     * 返回图片消息
     *
     * @param mediaId 根据素材id
     * @return
     */
    public ReturnWechatMessage imageMessageByMediaId(String mediaId) {
        return ImageReturnWechatMessage.builder().mediaId(mediaId).build();
    }

    /**
     * 返回音频消息
     *
     * @param voiceUrl 音频url
     * @return
     */
    public ReturnWechatMessage voiceMessage(String voiceUrl) {
        return VoiceReturnWechatMessage.builder().file(voiceUrl).build();
    }

    /**
     * 返回音频消息
     *
     * @param inputStream 音频流
     * @return
     */
    public ReturnWechatMessage voiceMessage(InputStream inputStream) {
        return VoiceReturnWechatMessage.builder().file(inputStream).build();
    }

    /**
     * 返回音频消息
     *
     * @param file 音频文件
     * @return
     */
    public ReturnWechatMessage voiceMessage(File file) {
        return VoiceReturnWechatMessage.builder().file(file).build();
    }

    /**
     * 根据素材id返回音频消息
     *
     * @param mediaId 素材id
     * @return
     */
    public ReturnWechatMessage voiceMessageByMediaId(String mediaId) {
        return VoiceReturnWechatMessage.builder().mediaId(mediaId).build();
    }

    /**
     * 返回视频消息
     *
     * @param videoUrl    视频地址
     * @param title       标题
     * @param description 描述
     * @return
     */
    public ReturnWechatMessage videoMessage(String videoUrl, String title, String description) {
        return VideoReturnWechatMessage.builder().file(videoUrl).description(description).title(title).build();
    }

    /**
     * 返回视频消息
     *
     * @param inputStream 视频流
     * @param title       标题
     * @param description 描述
     * @return
     */
    public ReturnWechatMessage videoMessage(InputStream inputStream, String title, String description) {
        return VideoReturnWechatMessage.builder().description(description).title(title).file(inputStream).build();
    }

    /**
     * 返回视频消息
     *
     * @param file        视频文件
     * @param title       标题
     * @param description 描述
     * @return
     */
    public ReturnWechatMessage videoMessage(File file, String title, String description) {
        return VideoReturnWechatMessage.builder().description(description).title(title).file(file).build();
    }

    /**
     * 根据素材id返回视频消息
     *
     * @param mediaId     素材id
     * @param title       标题
     * @param description 描述
     * @return
     */
    public ReturnWechatMessage videoMessageByMediaId(String mediaId, String title, String description) {
        return VideoReturnWechatMessage.builder().mediaId(mediaId).title(title).description(description).build();
    }

    /**
     * 返回音乐消息
     *
     * @param thubUrl     封面图url
     * @param title       标题
     * @param description 描述
     * @param musicUrl    音乐地址
     * @param HQMusicUrl  高清音乐地址
     * @return
     */
    public ReturnWechatMessage musicMessage(String thubUrl, String title, String description, String musicUrl, String HQMusicUrl) {
        return MusicReturnWechatMessage.builder().file(thubUrl).title(title).description(description).musicUrl(musicUrl).HQMusicUrl(HQMusicUrl).build();
    }

    /**
     * 返回音乐消息
     *
     * @param inputStream 封面图流
     * @param title       标题
     * @param description 描述
     * @param musicUrl    音乐地址
     * @param HQMusicUrl  高清音乐地址
     * @return
     */
    public ReturnWechatMessage musicMessage(InputStream inputStream, String title, String description, String musicUrl, String HQMusicUrl) {
        return MusicReturnWechatMessage.builder().file(inputStream).title(title).description(description).musicUrl(musicUrl).HQMusicUrl(HQMusicUrl).build();
    }

    /**
     * 返回音乐消息
     *
     * @param file        封面图文件
     * @param title       标题
     * @param description 描述
     * @param musicUrl    音乐地址
     * @param HQMusicUrl  高清音乐地址
     * @return
     */
    public ReturnWechatMessage musicMessage(File file, String title, String description, String musicUrl, String HQMusicUrl) {
        return MusicReturnWechatMessage.builder().file(file).title(title).description(description).musicUrl(musicUrl).HQMusicUrl(HQMusicUrl).build();
    }

    /**
     * 返回音乐消息
     *
     * @param thubMediaId 封面图素材id
     * @param title       标题
     * @param description 描述
     * @param musicUrl    音乐地址
     * @param HQMusicUrl  高清音乐地址
     * @return
     */
    public ReturnWechatMessage musicMessageByThubMediaId(String thubMediaId, String title, String description, String musicUrl, String HQMusicUrl) {
        return MusicReturnWechatMessage.builder().thumbMediaId(thubMediaId).title(title).description(description).musicUrl(musicUrl).HQMusicUrl(HQMusicUrl).build();
    }

    /**
     * 返回图文列表
     *
     * @param list 图文列表 利用方法中的builder构造结果
     * @return
     */
    public ReturnWechatMessage articleMessage(List<ArticleReturnWechatMessage.Articles> list) {
        return ArticleReturnWechatMessage.builder().articles(list).articleCount(list.size()).build();
    }

    public static ArticleListBuilder builder() {
        return new ArticleListBuilder();
    }


    public static class ArticleListBuilder {


        private static ArticleReturnWechatMessage.Articles articles = new ArticleReturnWechatMessage.Articles();

        private static List<ArticleReturnWechatMessage.Articles> list = new ArrayList<>();

        public ArticleListBuilder title(String title) {
            articles.setTitle(title);
            return this;
        }

        public ArticleListBuilder description(String description) {
            articles.setDescription(description);
            return this;
        }

        public ArticleListBuilder picUrl(String picUrl) {
            articles.setPicUrl(picUrl);
            return this;
        }

        public ArticleListBuilder url(String url) {
            articles.setUrl(url);
            return this;
        }

        public ArticleListBuilder build() {
            list.add(articles);
            articles = new ArticleReturnWechatMessage.Articles();
            return this;
        }

        public List<ArticleReturnWechatMessage.Articles> finish() {
            return list;
        }

    }


}
