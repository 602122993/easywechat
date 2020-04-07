package com.xiaoazhai.easywechat.util;

import com.xiaoazhai.easywechat.entity.message.respmsg.ReturnMessageInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhai
 * @date 2020年4月1日21:14:37
 * 自行拓展hutool工具
 */
public class BeanUtil extends cn.hutool.core.bean.BeanUtil {


    public static Map<String, Object> beanToMap(Object bean, boolean isToFirstUpperCase, boolean isToUnderLine, boolean ignoreNullValue) {
        Map<String, Object> map = beanToMap(bean, isToUnderLine, ignoreNullValue);
        if (isToFirstUpperCase) {
            Map<String, Object> resultMap = new HashMap<>();
            map.forEach((key, value) -> {
                if (value instanceof ReturnMessageInterface) {
                    resultMap.put(key.replace(key.charAt(0), Character.toUpperCase(key.charAt(0))), beanToMap(value, true, false, true));
                } else if (value instanceof List) {
                    StringBuilder sb = new StringBuilder();
                    ((List) value).forEach(v -> {
                        if (v instanceof ReturnMessageInterface) {
                            sb.append(beanToMap(value, true, false, true));
                        }
                    });
                    resultMap.put(key.replace(key.charAt(0), Character.toUpperCase(key.charAt(0))), sb);
                } else {
                    resultMap.put(key.replace(key.charAt(0), Character.toUpperCase(key.charAt(0))), value);
                }


            });
            return resultMap;
        }
        return map;
    }


}
