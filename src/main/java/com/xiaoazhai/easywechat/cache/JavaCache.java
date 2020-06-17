package com.xiaoazhai.easywechat.cache;

import com.xiaoazhai.easywechat.entity.response.AccessTokenResponse;
import com.xiaoazhai.easywechat.util.BeanUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhai
 * @date 2020年6月12日10:04:30
 * java内存缓存
 */
public class JavaCache implements CacheInterface {


    private static final Map<String, Map<String, Object>> CACHE_MAP = new ConcurrentHashMap<>();

    private static final Map<String, Long> TIME_STAMP = new ConcurrentHashMap<>();

    @Override
    public void set(String key, String hashKey, Object value, long time) {
        Map<String, Object> valueMap = CACHE_MAP.get(key);
        if (valueMap == null) {
            valueMap = new ConcurrentHashMap<>();
            CACHE_MAP.put(key, valueMap);
        }
        valueMap.put(hashKey, value);
        TIME_STAMP.put(key, System.currentTimeMillis() + 1000 * time);
    }

    @Override
    public <T> T get(String key, String hashKey, Class<T> clazz) {
        if (TIME_STAMP.get(key) != null && TIME_STAMP.get(key) < System.currentTimeMillis()) {
            return null;
        }
        Map<String, Object> valueMap = CACHE_MAP.get(key);
        if (valueMap == null) {
            return null;
        }
        Object value = valueMap.get(hashKey);
        if (clazz == String.class) {
            return (T) value;
        }
        return BeanUtil.toBean(value, clazz);
    }
}
