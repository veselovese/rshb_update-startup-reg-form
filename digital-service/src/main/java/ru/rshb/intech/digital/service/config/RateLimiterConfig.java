package ru.rshb.intech.digital.service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import ru.rshb.intech.digital.service.config.options.ApiTools;
import ru.rshb.intech.digital.service.rate.DebugRateLimiterService;
import ru.rshb.intech.digital.service.rate.RateLimitedResource;
import ru.rshb.intech.digital.service.rate.RateLimiterService;
import ru.rshb.intech.digital.service.rate.RateLimiterServiceImpl;

import java.util.List;

import static ru.rshb.intech.digital.service.rate.RateLimiterOptions.perMinute;

@Configuration
@RequiredArgsConstructor
public class RateLimiterConfig {

  private final ApiTools apiTools;

  @Bean
  @Profile({"!no-rate-limit"})
  public RateLimiterService rateLimiterService() {
    final var customLimits = List.of(
        new RateLimitedResource(HttpMethod.POST, apiTools.endpoint("/internship/registration"), perMinute(2)),
        new RateLimitedResource(HttpMethod.GET, apiTools.endpoint("/internship/registration/refs/fields"), perMinute(100)),
        new RateLimitedResource(HttpMethod.GET, apiTools.endpoint("/internship/registration/refs/cities"), perMinute(100)),
        new RateLimitedResource(HttpMethod.GET, apiTools.endpoint("/internship/registration/refs/education/universities"), perMinute(100)),
        new RateLimitedResource(HttpMethod.GET, apiTools.endpoint("/internship/registration/refs/education/stages"), perMinute(100)),
        new RateLimitedResource(HttpMethod.GET, apiTools.endpoint("/agrotech/olympiad/registration/refs/university"), perMinute(100)),
        new RateLimitedResource(HttpMethod.GET, apiTools.endpoint("/contests/patent/fish/registration/refs/cities"), perMinute(100)),
        new RateLimitedResource(HttpMethod.GET, apiTools.endpoint("/publication"), perMinute(100)),
        new RateLimitedResource(HttpMethod.PUT, apiTools.endpoint("/publication"), perMinute(100)),
        new RateLimitedResource(HttpMethod.GET, apiTools.endpoint("/patents/registration/refs/cities"), perMinute(100)),
        new RateLimitedResource(HttpMethod.GET, apiTools.endpoint("/refs/lookups/countries"), perMinute(100))
    );
    return new RateLimiterServiceImpl(customLimits);
  }

  @Bean
  @Profile({"no-rate-limit"})
  public RateLimiterService debugRateLimiterService() {
    return new DebugRateLimiterService();
  }
}
