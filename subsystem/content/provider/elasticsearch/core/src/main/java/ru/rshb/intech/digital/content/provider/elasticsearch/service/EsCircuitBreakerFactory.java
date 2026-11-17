package ru.rshb.intech.digital.content.provider.elasticsearch.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EsCircuitBreakerFactory {

  public static final String ES_CIRCUIT_BREAKER_ID = "elasticContentCircuitBreaker";

  private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;

  @Nonnull
  public CircuitBreaker circuitBreaker() {
    return circuitBreakerFactory.create(ES_CIRCUIT_BREAKER_ID);
  }
}
