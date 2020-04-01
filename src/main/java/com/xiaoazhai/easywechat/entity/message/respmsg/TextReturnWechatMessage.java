package com.xiaoazhai.easywechat.entity.message.respmsg;

import com.xiaoazhai.easywechat.util.XmlUtil;
import lombok.Data;

@Data
/**
 * @author zhai
 * @date 2020年4月1日21:11:34
 * 回复微信文本消息
 */
public class TextReturnWechatMessage extends ReturnWechatMessage {

    private String content;


    @Override
    public String getReturnMessage() {
        return XmlUtil.beanToXml(this, true);
    }
}
