package ru.rshb.intech.digital.service.rate;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RateBucketFactory {

  @Nonnull
  public static Bucket newBucketOf(@Nonnull RateLimiterOptions options) {
    return newBucketOf(options.getCapacity(), options.getRefillInterval());
  }

  @Nonnull
  public static Bucket newBucketOf(long tokensCapacity, @Nonnull Duration perInterval) {
    Refill refill = Refill.intervally(tokensCapacity, perInterval);
    Bandwidth limit = Bandwidth.classic(tokensCapacity, refill);
    return Bucket.builder()
        .addLimit(limit)
        .build();
  }
}
