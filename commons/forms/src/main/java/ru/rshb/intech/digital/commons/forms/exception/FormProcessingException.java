package ru.rshb.intech.digital.commons.forms.exception;

import ru.rshb.intech.digital.commons.utils.exceptiion.DigitalServiceException;

public class FormProcessingException extends DigitalServiceException {

  public FormProcessingException(String message, Throwable cause) {
    super(message, cause);
  }
}
