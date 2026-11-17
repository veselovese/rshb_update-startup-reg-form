package ru.rshb.intech.digital.commons.utils;

import ru.rshb.intech.digital.messages.I18n;

import jakarta.annotation.Nonnull;

/**
 * Общий тип ошибки.
 */
public enum ErrorType {

  UNEXPECTED_ERROR("common.error.internal"),

  VALIDATION_ERROR("common.error.validation"),

  INCORRECT_REQUEST("common.error.invalid.request.parameters"),

  RATE_LIMIT_EXCEEDED("common.error.rate.limit.exceeded");


  private final String titleKey;


  ErrorType(@Nonnull String msgKey) {
    this.titleKey = msgKey;
  }

  @Nonnull
  public String getTitle() {
    return I18n.of(titleKey);
  }
}
