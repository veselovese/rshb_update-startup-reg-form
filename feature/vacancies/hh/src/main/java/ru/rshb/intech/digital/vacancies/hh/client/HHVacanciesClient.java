package ru.rshb.intech.digital.vacancies.hh.client;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;
import ru.rshb.intech.digital.vacancies.hh.dto.auth.HHAuth;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.VacancyExtendedDto;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.list.VacancyItemDto;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.list.VacancyListPageDto;
import ru.rshb.intech.digital.vacancies.hh.exception.HHClientException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HHVacanciesClient extends HHSubClient {

  private static final String EMPLOYER_ID_PARAM = "employer_id";
  private static final String PER_PAGE_PARAM = "per_page";
  private static final String PER_PAGE_DEFAULT = "100";
  private static final String PAGE_PARAM = "page";
  private static final int INITIAL_PAGE_INDEX = 0;

  public HHVacanciesClient(@Nonnull RestTemplate restTemplate, @Nonnull HHClientOptions options) {
    super(restTemplate, options);
  }

  @Nonnull
  public List<VacancyItemDto> getAll(@Nonnull HHAuth auth, @Nonnull HHAccount account) {

    final var probePage = getAllByPage(auth, account, INITIAL_PAGE_INDEX);
    final var vacancies = new ArrayList<VacancyItemDto>();

    for (int pageIndex = 0; pageIndex < probePage.getPages(); pageIndex++) {
      final var page = getAllByPage(auth, account, pageIndex);
      vacancies.addAll(page.getItems());
    }

    return vacancies;
  }

  @Nonnull
  public VacancyListPageDto getAllByPage(@Nonnull HHAuth auth, @Nonnull HHAccount account, long pageIndex) {

    final var filter = HHFilter.create()
        .setParam(EMPLOYER_ID_PARAM, account.getId())
        .setParam(PER_PAGE_PARAM, PER_PAGE_DEFAULT)
        .setParam(PAGE_PARAM, Long.toString(pageIndex));

    final var endpoint = URI.create(HHEndpoint.vacancies(filter));
    log.debug("Endpoint: {}", endpoint);

    final var headers = authHeaders(auth);

    try {
      final var request = new RequestEntity<>(headers, HttpMethod.GET, endpoint);
      final var response = restTemplate().exchange(request, VacancyListPageDto.class);
      return response.getBody();
    } catch (Exception e) {
      throw new HHClientException("Vacancies fetch operation error", e);
    }
  }

  @Nonnull
  public VacancyExtendedDto getById(@Nonnull HHAuth auth, @Nonnull String vacancyId) {

    final var endpoint = URI.create(HHEndpoint.vacancy(vacancyId));
    log.debug("Endpoint: {}", endpoint);

    final var headers = authHeaders(auth);

    try {
      final var request = new RequestEntity<>(headers, HttpMethod.GET, endpoint);
      final var response = restTemplate().exchange(request, VacancyExtendedDto.class);
      return response.getBody();
    } catch (HHClientException e) {
      throw new HHClientException("Vacancy full info fetch operation error", e);
    }
  }
}
