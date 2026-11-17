package ru.rshb.intech.digital.service.rate;

import io.github.bucket4j.Bucket;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toMap;
import static ru.rshb.intech.digital.service.rate.RateBucketFactory.newBucketOf;

public class RateLimiterServiceImpl implements RateLimiterService {

  private final Map<RateLimitedResource, Bucket> cache;
  private final RateLimiterOptions defaultOptions;


  public RateLimiterServiceImpl(@Nonnull List<RateLimitedResource> customResources) {
    this(
        new RateLimiterOptions(),
        customResources.stream()
            .collect(toMap(r -> r, r -> newBucketOf(r.getRateLimit())))
    );
  }

  public RateLimiterServiceImpl(@Nonnull RateLimiterOptions defaultOptions,
                                @Nonnull Map<RateLimitedResource, Bucket> customLimits) {
    this.defaultOptions = defaultOptions;
    this.cache = new ConcurrentHashMap<>();
    this.cache.putAll(customLimits);
  }

  @Nonnull
  @Override
  public Bucket getBucketByResourceUri(@Nonnull RateLimitedResource resource) {
    var bucket = cache.get(resource);
    if (bucket == null) {
      bucket = newBucketOf(defaultOptions);
      cache.put(resource, bucket);
    }
    return bucket;
  }
}
