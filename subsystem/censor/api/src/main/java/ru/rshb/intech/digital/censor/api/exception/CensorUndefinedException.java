package ru.rshb.intech.digital.censor.api.exception;

import lombok.Generated;

@Generated
public class CensorUndefinedException extends RuntimeException {

  public CensorUndefinedException(String fieldName, String fieldValue) {
    super(String.format("Censor undefined value: [%s] = %s", fieldName, fieldValue));
  }
}
