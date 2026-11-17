package ru.rshb.intech.digital.vacancies.hh.client;

import jakarta.annotation.Nonnull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;
import ru.rshb.intech.digital.vacancies.hh.dto.auth.AuthRequest;
import ru.rshb.intech.digital.vacancies.hh.dto.auth.AuthState;
import ru.rshb.intech.digital.vacancies.hh.dto.auth.HHAuth;
import ru.rshb.intech.digital.vacancies.hh.exception.HHClientException;

import java.net.URI;

public class HHAuthClient extends HHSubClient {

  private static final String GRANT_TYPE_CLIENT_CREDS = "client_credentials";
  private static final String GRANT_TYPE_PARAM = "grant_type";
  private static final String CLIENT_ID_PARAM = "client_id";
  private static final String CLIENT_SECRET = "client_secret";

  public HHAuthClient(RestTemplate restTemplate, HHClientOptions options) {
    super(restTemplate, options);
  }

  @Nonnull
  public AuthState getAuthState(@Nonnull HHAuth auth) {

    final var endpoint = URI.create(HHEndpoint.ME);
    final var headers = authHeaders(auth);

    final var request = new RequestEntity<>(headers, HttpMethod.GET, endpoint);
    final var response = restTemplate().exchange(request, AuthState.class);

    return response.getBody();
  }


  @Nonnull
  public HHAuth authorize() {

    final var auth = new AuthRequest();
    auth.setGrantType(GRANT_TYPE_CLIENT_CREDS);
    auth.setClientId(options().getClientId());
    auth.setClientSecret(options().getClientSecret());

    final var headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

    final var urlParams = HHFilter.create()
        .setParam(GRANT_TYPE_PARAM, auth.getGrantType())
        .setParam(CLIENT_ID_PARAM, auth.getClientId())
        .setParam(CLIENT_SECRET, auth.getClientSecret());

    final var endpoint = URI.create(HHEndpoint.AUTH + urlParams);

    try {
      final var request = new RequestEntity<>(headers, HttpMethod.POST, endpoint);
      final var response = restTemplate().exchange(request, HHAuth.class);
      return response.getBody();
    } catch (Exception e) {
      throw HHClientException.authError(e);
    }
  }
}
