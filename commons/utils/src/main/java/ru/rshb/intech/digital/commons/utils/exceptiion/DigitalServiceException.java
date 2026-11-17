package ru.rshb.intech.digital.commons.utils.exceptiion;

/**
 * Родительский эксепшен для всех эксепшенов, выбрасываемых в сервисе Digital.
 */
public class DigitalServiceException extends RuntimeException {

  public DigitalServiceException() {
    super();
  }

  public DigitalServiceException(Throwable cause) {
    super(cause);
  }

  public DigitalServiceException(String message) {
    super(message);
  }

  public DigitalServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
