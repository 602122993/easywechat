package com.xiaoazhai.easywechat.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import org.springframework.util.StringUtils;

import java.util.*;

public class SignUtil {


    /**
     * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
     * 实现步骤: <br>
     *
     * @param paraMap    要排序的Map对象
     * @param urlEncode  是否需要URLENCODE
     * @param keyToLower 是否需要将Key转换为全小写
     *                   true:key转化成小写，false:不转化
     * @return
     * @throws Exception
     */
    public static <T> String getSign(T wxPayRequest, String secret) {
        String buff = sortByASCII(BeanUtil.beanToMap(wxPayRequest, true, true));
        System.out.println(buff + "&key=" + secret);
        return SecureUtil.md5(buff + "&key=" + secret);
    }

    public static String sortByASCII(Map<String, Object> paraMap) {
        String buff;
        Map<String, Object> tmpMap = paraMap;
        try {
            List<Map.Entry<String, Object>> infoIds = new ArrayList<>(tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, Comparator.comparing(o -> (o.getKey())));
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, Object> item : infoIds) {
                if (!StringUtils.isEmpty(item.getKey())) {
                    String key = item.getKey();
                    String val = item.getValue() == null ? "" : item.getValue().toString();
                    buf.append(key + "=" + val);
                    buf.append("&");
                }

            }
            buff = buf.toString();
            if (buff.isEmpty() == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            return null;
        }
        return buff;
    }

}
