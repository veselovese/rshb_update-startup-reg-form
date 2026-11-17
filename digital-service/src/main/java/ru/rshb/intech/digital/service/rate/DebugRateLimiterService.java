package ru.rshb.intech.digital.service.rate;

import io.github.bucket4j.Bucket;
import jakarta.annotation.Nonnull;

import java.util.Map;

public class DebugRateLimiterService implements RateLimiterService {

  private final RateLimiterService wrappedService;

  public DebugRateLimiterService() {
    this(new RateLimiterOptions());
  }

  public DebugRateLimiterService(@Nonnull RateLimiterOptions options) {
    this.wrappedService = new RateLimiterServiceImpl(options, Map.of());
  }

  @Nonnull
  @Override
  public Bucket getBucketByResourceUri(@Nonnull RateLimitedResource resource) {
    return wrappedService.getBucketByResourceUri(resource);
  }
}
