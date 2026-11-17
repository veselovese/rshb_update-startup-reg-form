package ru.rshb.intech.digital.subscriptions.core.exception;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.messages.I18n;

public class SubscriptionNotFoundException extends SubscriptionException {

  public SubscriptionNotFoundException(String message) {
    super(message);
  }

  /**
   * Ошибка активации подписки.
   *
   * @param activationCode Код активации.
   * @return Исключение.
   */
  @Nonnull
  public static SubscriptionNotFoundException activationError(@Nonnull String activationCode) {
    return new SubscriptionNotFoundException(
        I18n.of("subscriptions.error.activation.not.found", activationCode)
    );
  }

  /**
   * Ошибка деактивации подписки.
   *
   * @param deactivationCode Код деактивации.
   * @return Исключение.
   */
  @Nonnull
  public static SubscriptionNotFoundException deactivationError(@Nonnull String deactivationCode) {
    return new SubscriptionNotFoundException(
        I18n.of("subscriptions.error.deactivation.not.found", deactivationCode)
    );
  }
}
