package ru.rshb.intech.digital.vacancies.core.exception;

import ru.rshb.intech.digital.messages.I18n;

public class VacancyNotFoundException extends VacanciesException {

  private static final String MSG_KEY = "vacancies.error.not.found";

  public VacancyNotFoundException() {
    super(I18n.of(MSG_KEY));
  }
}
