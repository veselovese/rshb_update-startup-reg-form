package ru.rshb.intech.digital.vacancies.hh.client;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;
import ru.rshb.intech.digital.vacancies.hh.dto.auth.HHAuth;
import ru.rshb.intech.digital.vacancies.hh.dto.specialization.SpecializationGroupDto;
import ru.rshb.intech.digital.vacancies.hh.exception.HHClientException;

import java.net.URI;
import java.util.List;

@Slf4j
public class HHSpecializationsClient extends HHSubClient {


  public HHSpecializationsClient(RestTemplate restTemplate, HHClientOptions options) {
    super(restTemplate, options);
  }

  @Nonnull
  public List<SpecializationGroupDto> getAll(@Nonnull HHAuth auth) {

    final var endpoint = URI.create(HHEndpoint.SPECIALIZATIONS);
    log.info("Endpoint: {}", endpoint);

    final var headers = authHeaders(auth);

    try {
      final var request = RequestEntity
          .get(endpoint)
          .headers(headers)
          .build();
      final var typeRef = new ParameterizedTypeReference<List<SpecializationGroupDto>>() {
      };
      final var response = restTemplate().exchange(request, typeRef);

      return response.getBody();
    } catch (Exception e) {
      throw new HHClientException("Specializations fetching error", e);
    }
  }
}
