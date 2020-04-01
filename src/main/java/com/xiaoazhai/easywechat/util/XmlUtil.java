package com.xiaoazhai.easywechat.util;

import cn.hutool.core.bean.copier.CopyOptions;

import java.util.HashMap;
import java.util.Map;

public class XmlUtil extends cn.hutool.core.util.XmlUtil {


    public static String mapToXml(Map<String, Object> data) {
        return mapToXmlStr(data, "xml");
    }

    public static <T> T xmlToBean(String xml, boolean isToHump, Class<T> clazz, boolean ignoreNull) {
        Map<String, Object> map = xmlToMap(xml);
        Map<String, Object> result = new HashMap<>();
        if (isToHump) {
            map.forEach((key, value) -> {
                if (key.length() > 0 && Character.isUpperCase(key.charAt(0))) {
                    result.put(key.replace(key.charAt(0), Character.toLowerCase(key.charAt(0))), value);
                } else {
                    result.put(underlineToHump(key), value);
                }
            });
        }
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(ignoreNull);
        return BeanUtil.mapToBean(result, clazz, copyOptions);
    }

    public static <T> String beanToXml(T bean, boolean isToFirstUpperCase) {
        String result = "";
        try {
            result = mapToXml(BeanUtil.beanToMap(bean, isToFirstUpperCase, true, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <T> String beanToXml(T bean) {
        return beanToXml(bean, false);
    }

    /**
     * 下划线转驼峰
     *
     * @param para
     * @return
     */
    public static String underlineToHump(String para) {
        StringBuilder result = new StringBuilder();
        String a[] = para.split("_");
        for (String s : a) {
            if (!para.contains("_")) {
                result.append(s);
                continue;
            }
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
}
