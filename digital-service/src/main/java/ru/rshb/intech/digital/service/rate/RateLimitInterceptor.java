package ru.rshb.intech.digital.service.rate;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.rshb.intech.digital.commons.utils.ErrorType;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

@RequiredArgsConstructor
public class RateLimitInterceptor implements HandlerInterceptor {

  private final RateLimiterService rateLimiterService;

  @Override
  public boolean preHandle(@Nonnull HttpServletRequest request,
                           @Nonnull HttpServletResponse response,
                           @Nonnull Object handler) throws Exception {

    final var resource = new RateLimitedResource(
        HttpMethod.valueOf(request.getMethod()),
        request.getRequestURI()
    );

    if (containsIgnoreCase(request.getRequestURI(), "/lookups/countries")) {
      return true;
    }

    final Bucket bucket = rateLimiterService.getBucketByResourceUri(resource);
    final ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
    if (probe.isConsumed()) {
      return true;
    }

    response.sendError(
        HttpStatus.TOO_MANY_REQUESTS.value(),
        ErrorType.RATE_LIMIT_EXCEEDED.getTitle()
    );
    return false;
  }
}
