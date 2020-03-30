package com.xiaoazhai.easywechat.annotation;

import java.lang.annotation.*;

/**
 * @author zhai
 * @date 2020年3月30日18:48:15
 * 微信消息转化类
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Message {
}
