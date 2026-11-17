package ru.rshb.intech.digital.commons.forms.exception;

import ru.rshb.intech.digital.messages.I18n;

public class AttributeMaxLengthExceededException extends AttributeValidationException {

  private static final String MESSAGE_TEMPLATE = "validation.error.attribute.max.length.exceeded";

  public AttributeMaxLengthExceededException(String attributeName) {
    super(I18n.of(MESSAGE_TEMPLATE, attributeName));
  }
}
