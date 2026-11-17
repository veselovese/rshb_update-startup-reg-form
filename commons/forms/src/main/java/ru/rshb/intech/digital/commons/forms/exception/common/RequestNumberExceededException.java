package ru.rshb.intech.digital.commons.forms.exception.common;

import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.messages.I18n;

/**
 * Превышено максимально допустимое количество заявок.
 */
public class RequestNumberExceededException extends AttributeBusinessRulesException {

  private static final String MSG_KEY = "common.error.request.limit.exceeded";

  public RequestNumberExceededException() {
    super(I18n.of(MSG_KEY));
  }

}
