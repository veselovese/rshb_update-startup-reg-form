package ru.rshb.intech.digital.feature.innovations.core.exception;

import lombok.Getter;
import ru.rshb.intech.digital.messages.I18n;

import java.util.HashMap;
import java.util.Map;

@Getter
public class InnovationValidationException extends InnovationsException {

  private static final String MSG_KEY = "common.error.validation";

  private final Map<String, String> violations = new HashMap<>();

  public InnovationValidationException(String field, String violation) {
    super(I18n.of(MSG_KEY));
    this.violations.put(field, violation);
  }
}
