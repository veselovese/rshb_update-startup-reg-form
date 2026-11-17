package ru.rshb.intech.digital.commons.forms.exception;

import ru.rshb.intech.digital.messages.I18n;

public class AttributeValueMissingException extends AttributeValidationException {

  private static final String MESSAGE_TEMPLATE = "validation.error.attribute.value.missing";


  public AttributeValueMissingException(String attributeName) {
    super(I18n.of(MESSAGE_TEMPLATE, attributeName));
  }

  public AttributeValueMissingException(String attributeName, Throwable e) {
    super(I18n.of(MESSAGE_TEMPLATE, attributeName), e);
  }
}
