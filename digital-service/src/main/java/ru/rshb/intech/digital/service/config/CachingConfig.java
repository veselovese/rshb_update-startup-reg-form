package ru.rshb.intech.digital.service.config;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CachingConfig {

  @Bean
  public Caffeine<Object, Object> caffeineConfig() {
    return Caffeine.newBuilder()
        .expireAfterWrite(7, TimeUnit.DAYS);
  }

  @Bean
  public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
    final String[] cacheNames = {
        "publicationIndexCache"
    };
    final var cacheManager = new CaffeineCacheManager(cacheNames);
    cacheManager.setCaffeine(caffeine);
    return cacheManager;
  }
}
