package ru.rshb.intech.digital.service.rate;

import io.github.bucket4j.Bucket;
import jakarta.annotation.Nonnull;

public interface RateLimiterService {

  @Nonnull
  Bucket getBucketByResourceUri(@Nonnull RateLimitedResource resource);
}
