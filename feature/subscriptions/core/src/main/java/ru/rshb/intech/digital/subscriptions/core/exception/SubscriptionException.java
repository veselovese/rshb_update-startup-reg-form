package ru.rshb.intech.digital.subscriptions.core.exception;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.utils.exceptiion.DigitalServiceException;

public class SubscriptionException extends DigitalServiceException {

  public SubscriptionException(String message) {
    super(message);
  }

  public SubscriptionException(String message, Throwable cause) {
    super(message, cause);
  }

  @Nonnull
  public static SubscriptionException noCategoriesFound() {
    return new SubscriptionException("No categories found");
  }
}
