package com.xiaoazhai.easywechat.config;


import com.xiaoazhai.easywechat.cache.CacheInterface;
import com.xiaoazhai.easywechat.cache.JavaCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * @author zhai
 * @date 2020年6月12日09:52:37
 * 缓存配置
 */
public class EasyWxCacheConfig {


    @Bean("easyWxCache")
    public CacheInterface javaCache() {
        return new JavaCache();
    }

}
