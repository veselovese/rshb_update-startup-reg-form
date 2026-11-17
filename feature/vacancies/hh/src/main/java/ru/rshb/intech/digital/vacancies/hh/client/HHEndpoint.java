package ru.rshb.intech.digital.vacancies.hh.client;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HHEndpoint {

  public static final String BASE_URL = "https://api.hh.ru";
  public static final String AUTH = "https://hh.ru/oauth/token";
  public static final String ME = BASE_URL + "/me";
  public static final String VACANCIES = "/vacancies";
  public static final String SPECIALIZATIONS = BASE_URL + "/specializations";
  public static final String PROF_ROLES = BASE_URL + "/professional_roles";


  @Nonnull
  public static String vacancies(@Nonnull String employerId) {
    return BASE_URL + VACANCIES + String.format("?employer_id=%s", employerId);
  }

  @Nonnull
  public static String vacancies(@Nonnull String employerId, @Nonnull Long perPage, @Nonnull Long page) {
    return vacancies(employerId) + String.format("&per_page=%s&page=%s", perPage, page);
  }

  @Nonnull
  public static String vacancies(@Nonnull HHFilter filter) {
    return BASE_URL + VACANCIES + filter;
  }

  @Nonnull
  public static String vacancy(@Nonnull String vacancyId) {
    return BASE_URL + VACANCIES + "/" + vacancyId;
  }
}
