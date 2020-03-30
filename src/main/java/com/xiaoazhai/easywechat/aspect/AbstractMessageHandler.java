package com.xiaoazhai.easywechat.aspect;


import com.xiaoazhai.easywechat.entity.message.BaseWechatMessage;

import java.util.HashMap;
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

    /**
     * 消息处理
     *
     * @param message
     */
    public abstract void onMessage(T message);


}
