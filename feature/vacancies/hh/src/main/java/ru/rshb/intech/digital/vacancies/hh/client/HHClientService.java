package ru.rshb.intech.digital.vacancies.hh.client;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.rshb.intech.digital.vacancies.hh.dto.auth.HHAuth;
import ru.rshb.intech.digital.vacancies.hh.dto.profrole.ProfRoleCategoryDto;
import ru.rshb.intech.digital.vacancies.hh.dto.specialization.SpecializationGroupDto;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.VacancyExtendedDto;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.list.VacancyItemDto;
import ru.rshb.intech.digital.vacancies.hh.exception.HHClientException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RequiredArgsConstructor
public class HHClientService {

  private static final String BEARER_TOKEN_TYPE = "bearer";

  private final HHClient client;
  private final AtomicReference<HHAuth> authRef = new AtomicReference<>();


  public HHClientService(@Nonnull HHClient client, @Nonnull String authToken) {
    this(client);
    this.authRef.set(new HHAuth(authToken, BEARER_TOKEN_TYPE));
  }


  @Nonnull
  public HHAuth getAuth() {

    // 1. Если еще не аутентифицировались.
    if (authRef.get() == null) {
      return auth();
    }

    // 2. Пробный запрос со старой аутентификацией
    final var auth = authRef.get();
    try {
      client.authentication().getAuthState(auth);
    } catch (Exception e) {
      throw HHClientException.probeRequestError(e);
    }

    return auth;
  }

  @Nonnull
  private HHAuth auth() {
    final var auth = client.authentication().authorize();
    authRef.set(auth);
    log.debug(auth.getAccessToken());
    return auth;
  }

  @Nonnull
  public List<VacancyItemDto> findAllVacancies(@Nonnull List<HHAccount> accounts) {

    if (accounts.isEmpty()) {
      log.warn("No HH accounts provided");
      return Collections.emptyList();
    }

    try {
      final var auth = getAuth();
      final var results = new ArrayList<VacancyItemDto>();
      for (final var account : accounts) {
        final var vacancies = client.vacancies().getAll(auth, account);
        results.addAll(vacancies);
      }
      return results;
    } catch (Exception e) {
      throw HHClientException.vacanciesFetchError(e);
    }
  }

  @Nonnull
  public List<VacancyItemDto> findAllVacanciesByPage(@Nonnull HHAccount account, int pageIndex) {
    try {
      final var auth = getAuth();
      final var page = client.vacancies().getAllByPage(auth, account, pageIndex);
      return page.getItems();
    } catch (Exception e) {
      throw HHClientException.vacanciesPageFetchError(e);
    }
  }

  @Nonnull
  public Optional<VacancyExtendedDto> findVacancyById(@Nonnull String vacancyId) {
    try {
      final var auth = getAuth();
      final var vacancy = client.vacancies().getById(auth, vacancyId);
      return Optional.of(vacancy);
    } catch (Exception e) {
      throw HHClientException.vacancyFetchError(vacancyId, e);
    }
  }

  @Nullable
  public VacancyExtendedDto getDetailedVacancyById(@Nonnull String vacancyId) {
    return findVacancyById(vacancyId)
        .orElse(null);
  }

  @Nonnull
  public List<SpecializationGroupDto> getSpecializations() {
    try {
      final var auth = getAuth();
      return client.specializations().getAll(auth);
    } catch (Exception e) {
      throw HHClientException.specializationsFetchError(e);
    }
  }

  @Nonnull
  public List<ProfRoleCategoryDto> getProfRoleCategories() {
    try {
      final var auth = getAuth();
      return client.professionalRoles().getAll(auth);
    } catch (Exception e) {
      throw HHClientException.profRolesFetchError(e);
    }
  }
}
