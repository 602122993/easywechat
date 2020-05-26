package com.xiaoazhai.easywechat.util;

import com.xiaoazhai.easywechat.entity.message.InnerMessageClass;

/**
 * @author zhai
 * @date 2020年5月8日21:50:49 封装hutool反射工具包
 * 反射工具包
 */
public class ReflectUtil extends cn.hutool.core.util.ReflectUtil {

    public void paddingField(Class clazz, String key, Object value) {
        Class<?> innerClazz = getField(clazz, key).getType();
        InnerMessageClass anno = innerClazz.getAnnotation(InnerMessageClass.class);
//        if(anno)
    }


}
