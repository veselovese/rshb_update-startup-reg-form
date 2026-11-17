package ru.rshb.intech.digital.subsystem.audit.notifications;

import ru.rshb.intech.digital.commons.utils.exceptiion.DigitalServiceException;

public class NotificationLogException extends DigitalServiceException {

  public NotificationLogException(String message, Throwable cause) {
    super(message, cause);
  }
}
