package ru.rshb.intech.digital.vacancies.hh.exception;

import jakarta.annotation.Nonnull;

public class HHClientException extends RuntimeException {

  public HHClientException(String message, Throwable cause) {
    super(message, cause);
  }

  @Nonnull
  public static HHClientException authError(Throwable throwable) {
    return new HHClientException("HH auth error", throwable);
  }

  @Nonnull
  public static HHClientException probeRequestError(Throwable throwable) {
    return new HHClientException("HH probe request error", throwable);
  }

  @Nonnull
  public static HHClientException vacanciesFetchError(Throwable throwable) {
    return new HHClientException("Vacancies fetching error", throwable);
  }

  @Nonnull
  public static HHClientException vacanciesPageFetchError(Throwable throwable) {
    return new HHClientException("Vacancies page fetching error", throwable);
  }

  @Nonnull
  public static HHClientException vacancyFetchError(@Nonnull String vacancyId, Throwable throwable) {
    return new HHClientException("Vacancy fetch error (id=" + vacancyId + ")", throwable);
  }

  @Nonnull
  public static HHClientException specializationsFetchError(Throwable throwable) {
    return new HHClientException("Vacancy specializations fetching error", throwable);
  }

  @Nonnull
  public static HHClientException profRolesFetchError(Throwable throwable) {
    return new HHClientException("Professional roles fetching error", throwable);
  }
}
