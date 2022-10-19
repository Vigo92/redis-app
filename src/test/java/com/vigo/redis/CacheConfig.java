package com.vigo.redis;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Obia Ugochukwu Vigo
 * email : ugochukwu.obia@teamapt.com
 * date : 19/10/2022
 **/

@Configuration
public class CacheConfig {

    @Bean
    public ConcurrentMapCacheManager cacheManager(){

        return new ConcurrentMapCacheManager("itemCache","customerCache");
    }
}
