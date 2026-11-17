package ru.rshb.intech.digital.service.rate;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RateLimiterOptions {

  private int capacity = 10;
  private Duration refillInterval = Duration.ofMinutes(1);

  @Nonnull
  public static RateLimiterOptions perMinute(int capacity) {
    return new RateLimiterOptions(capacity, Duration.ofMinutes(1));
  }
}
