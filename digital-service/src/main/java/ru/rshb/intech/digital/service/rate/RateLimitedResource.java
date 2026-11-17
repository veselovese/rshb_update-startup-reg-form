package ru.rshb.intech.digital.service.rate;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"method", "resourceUri"})
public class RateLimitedResource {

  private HttpMethod method;
  private String resourceUri;
  private RateLimiterOptions rateLimit;

  public RateLimitedResource(@Nonnull HttpMethod method, @Nonnull String resourceUri) {
    setMethod(method);
    setResourceUri(resourceUri);
    setRateLimit(null);
  }
}
