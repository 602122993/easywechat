package com.xiaoazhai.easywechat.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author zhai
 * @date 2020年3月30日19:08:54
 * 消息处理请求标记
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MessageRequest {
}
