package ru.rshb.intech.digital.service.test;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import ru.rshb.intech.digital.service.rate.DebugRateLimiterService;
import ru.rshb.intech.digital.service.rate.RateLimiterOptions;
import ru.rshb.intech.digital.service.rate.RateLimiterService;

import java.time.Duration;

@TestConfiguration
public class TestRateLimiterConfig {

    @Bean
    @Primary
    public RateLimiterService rateLimiterService() {
        return new DebugRateLimiterService(new RateLimiterOptions(100, Duration.ofMinutes(1)));
    }
}
