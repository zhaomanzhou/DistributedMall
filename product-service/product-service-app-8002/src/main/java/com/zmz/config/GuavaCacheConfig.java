package com.zmz.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class GuavaCacheConfig {

    @Bean
    public CacheManager cacheManager() {
        GuavaCacheConfig cacheConfig = new GuavaCacheConfig();


        return cacheConfig.cacheManager();
    }
}
