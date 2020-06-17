package com.xiaoazhai.easywechat.cache;

/**
 * @author zhai
 * @date 2020年6月12日09:57:33
 * 缓存抽象接口
 */
public interface CacheInterface {
    /**
     * 存放
     *
     * @param key
     * @param hashKey
     * @param value
     */
    void set(String key,String hashKey, Object value,long time);

    /**
     * 取出
     *
     * @param key
     * @param clazz
     * @return t
     */
    <T> T get(String key,String hashKey, Class<T> clazz);


}
