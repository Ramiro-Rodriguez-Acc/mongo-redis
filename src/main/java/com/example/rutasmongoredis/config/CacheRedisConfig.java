package com.example.rutasmongoredis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class CacheRedisConfig {

    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> cacheConfigMap = new HashMap<>();
        cacheConfigMap.put("reporte", new CacheConfig(600000, 0));
        cacheConfigMap.put("tramo", new CacheConfig(600000, 0));
        return new RedissonSpringCacheManager(redissonClient, cacheConfigMap);
    }
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + System.getenv("SPRING_REDIS_HOST") + ":" + System.getenv("SPRING_REDIS_PORT"));
        return Redisson.create(config);
    }
}
