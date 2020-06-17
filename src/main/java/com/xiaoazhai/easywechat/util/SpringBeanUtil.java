package com.xiaoazhai.easywechat.util;

import com.xiaoazhai.easywechat.annotation.MessageHandler;
import com.xiaoazhai.easywechat.aspect.AbstractMessageHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

@Component("easyWxSpringBeanUtil")
public class SpringBeanUtil implements ApplicationContextAware, ApplicationListener {


    /**
     * 直接通过Spring 上下文获取SpringBean,用于多线程环境
     */
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringBeanUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象 这里重写了bean方法，起主要作用
     * example: getBean("userService")//注意： 类名首字母一定要小写！
     */
    public static <T> T getBean(String beanId) throws BeansException {
        return (T) applicationContext.getBean(beanId);
    }


    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        Map<String, Object> bean = applicationContext.getBeansWithAnnotation(MessageHandler.class);
        bean.forEach((key, value) -> {
            if (value instanceof AbstractMessageHandler) {
                Type t = value.getClass().getGenericSuperclass();
                if (t instanceof ParameterizedType) {
                    Type[] p = ((ParameterizedType) t).getActualTypeArguments();
                    AbstractMessageHandler.registerMap.put(((Class) p[0]).getName(), (AbstractMessageHandler) value);
                }
            }
        });
    }
}
