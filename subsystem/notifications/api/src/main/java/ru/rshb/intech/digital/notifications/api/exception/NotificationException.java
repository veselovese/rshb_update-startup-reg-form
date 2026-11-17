package ru.rshb.intech.digital.notifications.api.exception;

public class NotificationException extends RuntimeException {

  public NotificationException(String message) {
    super(message);
  }

  public NotificationException(String message, Throwable cause) {
    super(message, cause);
  }
}
