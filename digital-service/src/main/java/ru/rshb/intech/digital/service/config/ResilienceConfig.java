package ru.rshb.intech.digital.service.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rshb.intech.digital.content.provider.elasticsearch.service.EsCircuitBreakerFactory;

import java.time.Duration;

@Configuration
public class ResilienceConfig {

  @Bean
  public Customizer<Resilience4JCircuitBreakerFactory> elasticContentCircuitBreakerConfig() {

    final var circuitBreakerConfig = CircuitBreakerConfig.custom()
        .failureRateThreshold(50) // TODO: вынести параметры
        .waitDurationInOpenState(Duration.ofMillis(1000))
        .slidingWindowSize(2)
        .build();

    final var timeLimiterConfig = TimeLimiterConfig.custom()
        .timeoutDuration(Duration.ofSeconds(2))
        .build();

    return factory -> factory.configure(
        builder -> builder
            .circuitBreakerConfig(circuitBreakerConfig)
            .timeLimiterConfig(timeLimiterConfig)
            .build(),
        EsCircuitBreakerFactory.ES_CIRCUIT_BREAKER_ID
    );
  }

}
