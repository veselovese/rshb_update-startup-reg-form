package ru.rshb.intech.digital.notifications.mail.exception;


import ru.rshb.intech.digital.commons.utils.exceptiion.DigitalServiceException;

public class MailNotificationException extends DigitalServiceException {

  public MailNotificationException(String message, Throwable cause) {
    super(message, cause);
  }
}
