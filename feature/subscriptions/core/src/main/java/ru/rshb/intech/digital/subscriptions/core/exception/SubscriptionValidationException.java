package ru.rshb.intech.digital.subscriptions.core.exception;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.messages.I18n;

public class SubscriptionValidationException extends SubscriptionException {

  public SubscriptionValidationException(String message) {
    super(message);
  }

  /**
   * Не указаны разделы для подписки.
   *
   * @return Исключение.
   */
  @Nonnull
  public static SubscriptionValidationException categoriesMissing() {
    return new SubscriptionValidationException(
        I18n.of("subscriptions.error.validation.categories.missing")
    );
  }

  /**
   * Некорректный список категорий.
   *
   * @return Исключение.
   */
  public static SubscriptionValidationException unknownCategories() {
    return new SubscriptionValidationException(
        I18n.of("subscriptions.error.validation.categories.unknown")
    );
  }

  /**
   * Запрос на подписку отклонен.
   *
   * @param email Адрес электронной почты.
   * @return Исключение.
   */
  @Nonnull
  public static SubscriptionValidationException emailBanned(@Nonnull String email) {
    return new SubscriptionValidationException(
        I18n.of("subscriptions.error.validation.email.banned", email)
    );
  }
}
