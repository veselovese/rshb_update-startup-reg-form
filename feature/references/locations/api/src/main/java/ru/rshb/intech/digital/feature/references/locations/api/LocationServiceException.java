package ru.rshb.intech.digital.feature.references.locations.api;

import ru.rshb.intech.digital.commons.utils.exceptiion.DigitalServiceException;

public class LocationServiceException extends DigitalServiceException {

  public LocationServiceException(Throwable cause) {
    super(cause);
  }

  public LocationServiceException(String message) {
    super(message);
  }

  public LocationServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
