package com.xiaoazhai.easywechat.util;

import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.json.JSONUtil;
import com.xiaoazhai.easywechat.entity.message.respmsg.NeedRecursionInterface;
import com.xiaoazhai.easywechat.entity.message.respmsg.ReturnMessageInterface;
import com.xiaoazhai.easywechat.entity.request.BaseCreateCardRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
        Map<String, Object> resultMap = new HashMap<>();
        map.forEach((key, value) -> {
            if (value instanceof ReturnMessageInterface) {
                if (isToFirstUpperCase) {
                    resultMap.put(key.replace(key.charAt(0), Character.toUpperCase(key.charAt(0))), beanToMap(value, isToFirstUpperCase, isToUnderLine, true));
                } else {
                    resultMap.put(key, beanToMap(value, isToFirstUpperCase, isToUnderLine, true));
                }
            } else if (value instanceof List) {
                List list = new ArrayList();
                ((List) value).forEach(v -> {
                    if (v instanceof ReturnMessageInterface) {
                        list.add(beanToMap(v, isToFirstUpperCase, isToUnderLine, true));
                    }
                });

                if (isToFirstUpperCase) {
                    resultMap.put(key.replace(key.charAt(0), Character.toUpperCase(key.charAt(0))), list);
                } else {
                    resultMap.put(key, list);
                }
            } else {
                if (isToFirstUpperCase) {
                    resultMap.put(key.replace(key.charAt(0), Character.toUpperCase(key.charAt(0))), value);
                } else {
                    resultMap.put(key, value);
                }
            }


        });
        return resultMap;
    }


    /**
     * 忽略空值 泛型
     *
     * @param source
     * @param target
     */
    public static <T> T copyIgnoreNullPropertiesGeneric(Object source, T target) {
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(true);
        copyProperties(source, target, copyOptions);
        return target;
    }

    /**
     * 递归maptobean
     *
     * @param bean
     * @param isToUnderlineCase
     * @param ignoreNullValue
     */
    public static Map beanToMapRecursion(Object bean, boolean isToUnderlineCase, boolean ignoreNullValue) {
        Map<String, Object> map = beanToMap(bean, isToUnderlineCase, ignoreNullValue);
        map.forEach((key, value) -> {
            if (value instanceof NeedRecursionInterface) {
                map.put(key, beanToMapRecursion(value, isToUnderlineCase, ignoreNullValue));
            }
            if ("Abstract".equals(key)) {
                map.put("abstract", value);
                map.remove("Abstract");
            }
        });
        return map;
    }
}
