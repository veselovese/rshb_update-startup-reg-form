package ru.rshb.intech.digital.commons.forms.exception;

public class AttributeValidationException extends RuntimeException {

  public AttributeValidationException(String message) {
    super(message);
  }

  public AttributeValidationException(String message, Throwable cause) {
    super(message, cause);
  }
}
