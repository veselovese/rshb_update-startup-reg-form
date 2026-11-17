package ru.rshb.intech.digital.vacancies.hh.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import ru.rshb.intech.digital.vacancies.hh.dto.auth.HHAuth;

import jakarta.annotation.Nonnull;

@RequiredArgsConstructor
public abstract class HHSubClient {

  private static final String HH_USER_AGENT_HEADER = "HH-User-Agent";

  private final RestTemplate restTemplate;
  private final HHClientOptions options;

  @Nonnull
  protected RestTemplate restTemplate() {
    return restTemplate;
  }

  @Nonnull
  protected HHClientOptions options() {
    return options;
  }

  @Nonnull
  protected HttpHeaders authHeaders(@Nonnull HHAuth auth) {
    final var headers = new HttpHeaders();
    headers.add(
        HttpHeaders.AUTHORIZATION,
        String.format("%s %s", auth.getTokenTypeCapitalized(), auth.getAccessToken())
    );
    headers.add(
        HH_USER_AGENT_HEADER,
        options.getUserAgent()
    );
    return headers;
  }
}
