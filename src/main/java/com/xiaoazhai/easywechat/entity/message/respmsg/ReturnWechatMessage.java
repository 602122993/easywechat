package com.xiaoazhai.easywechat.entity.message.respmsg;

import com.xiaoazhai.easywechat.entity.message.BaseWechatMessage;
import lombok.Data;

/**
 * @author zhai
 * @date 2020年3月30日22:04:20
 * 返回微信消息
 */
@Data
public abstract class ReturnWechatMessage extends BaseWechatMessage {



    /**
     * 获取返回值消息
     *
     * @return
     */
    public abstract String getReturnMessage();

}
