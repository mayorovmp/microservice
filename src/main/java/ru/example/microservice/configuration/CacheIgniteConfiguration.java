package ru.example.microservice.configuration;

import org.apache.ignite.cache.spring.SpringCacheManager;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.expiry.Duration;
import javax.cache.expiry.ModifiedExpiryPolicy;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheIgniteConfiguration {
    @Bean
    public SpringCacheManager cacheManager() {
        SpringCacheManager mgr = new SpringCacheManager();

        var cacheCfg = new CacheConfiguration<String, String>("version");

        cacheCfg.setExpiryPolicyFactory(ModifiedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 30)));

        var cfg = new IgniteConfiguration()
                .setCacheConfiguration(cacheCfg);

        mgr.setConfiguration(cfg);

        return mgr;
    }
}
