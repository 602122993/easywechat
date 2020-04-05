package com.xiaoazhai.easywechat.entity.message.respmsg;

import cn.hutool.core.util.ReUtil;
import com.xiaoazhai.easywechat.enums.MsgTypeEnum;
import com.xiaoazhai.easywechat.util.BeanUtil;
import com.xiaoazhai.easywechat.util.XmlUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhai
 * @date 2020年4月1日21:29:34
 * 返回图片素材
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleReturnWechatMessage extends ReturnWechatMessage {

    /**
     * 图片内部类
     */
    private List<Articles> articles;


    private Integer articleCount;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Articles implements ReturnMessageInterface {
        /**
         * 标题
         */
        private String title;
        /**
         * 描述
         */
        private String description;
        /**
         * 图片地址
         */
        private String picUrl;
        /**
         * 文章地址
         */
        private String url;
    }

    @Override
    public String getReturnMessage() {
        doEmpty();
        setMsgType(MsgTypeEnum.article);
        initMessage(this);
        return XmlUtil.beanToXml(this, true);
    }

    private void doEmpty() {
        articles = null;
    }


}
