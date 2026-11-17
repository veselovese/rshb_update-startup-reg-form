package ru.rshb.intech.digital.vacancies.model;

import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Generated
@RequiredArgsConstructor
public enum VacancyFilterType {

  SORT("sort"),

  COMPANIES("companies"),

  FIELDS("fields"),

  EMPLOYMENT("employment"),

  EXPERIENCE("experience");

  private final String key;
}
