package ru.rshb.intech.digital.feature.innovations.core.exception;

import ru.rshb.intech.digital.messages.I18n;

/**
 * Запрос на инновации не найден.
 */
public class DemandNotFoundException extends InnovationsException {

  private static final String MSG_KEY = "innovations.demand.error.not.found";

  public DemandNotFoundException(String demandId) {
    super(I18n.of(MSG_KEY, demandId));
  }
}
