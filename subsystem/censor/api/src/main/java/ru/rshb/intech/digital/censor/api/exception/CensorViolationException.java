package ru.rshb.intech.digital.censor.api.exception;

import lombok.Generated;

@Generated
public class CensorViolationException extends CensorException {

  public CensorViolationException(String fieldName, String fieldValue) {
    super(String.format("Censor violation exception: [%s] = %s", fieldName, fieldValue));
  }
}
