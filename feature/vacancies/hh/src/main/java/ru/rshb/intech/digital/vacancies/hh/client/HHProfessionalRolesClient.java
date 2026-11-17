package ru.rshb.intech.digital.vacancies.hh.client;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;
import ru.rshb.intech.digital.vacancies.hh.dto.auth.HHAuth;
import ru.rshb.intech.digital.vacancies.hh.dto.profrole.ProfRoleCategoryDto;
import ru.rshb.intech.digital.vacancies.hh.dto.profrole.ProfRoleCategoryListDto;
import ru.rshb.intech.digital.vacancies.hh.exception.HHClientException;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
public class HHProfessionalRolesClient extends HHSubClient {

  public HHProfessionalRolesClient(RestTemplate restTemplate, HHClientOptions options) {
    super(restTemplate, options);
  }

  @Nonnull
  public List<ProfRoleCategoryDto> getAll(@Nonnull HHAuth auth) {

    final var endpoint = URI.create(HHEndpoint.PROF_ROLES);
    log.info("Endpoint: {}", endpoint);

    final var headers = authHeaders(auth);

    try {
      final var request = RequestEntity
          .get(endpoint)
          .headers(headers)
          .build();

      final var response = restTemplate().exchange(request, ProfRoleCategoryListDto.class);

      return Optional.ofNullable(response.getBody())
          .map(body -> body.getCategories())
          .orElse(Collections.emptyList());

    } catch (Exception e) {
      throw new HHClientException("Specializations fetching error", e);
    }
  }
}
