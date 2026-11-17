package ru.rshb.intech.digital.commons.forms.exception;

import ru.rshb.intech.digital.messages.I18n;

/**
 * Некорректный формат значения поля.
 */
public class AttributeBadFormatException extends AttributeValidationException {

  private static final String MESSAGE_TEMPLATE = "validation.error.attribute.data.format";

  public AttributeBadFormatException(String attributeName) {
    super(I18n.of(MESSAGE_TEMPLATE, attributeName));
  }

  public AttributeBadFormatException(String attributeName, Throwable e) {
    super(I18n.of(MESSAGE_TEMPLATE, attributeName), e);
  }
}
