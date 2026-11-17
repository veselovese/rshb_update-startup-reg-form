package ru.rshb.intech.digital.commons.validation.telegram;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static ru.rshb.intech.digital.commons.validation.telegram.TelegramUtils.isValidLink;

public class TelegramValidator implements ConstraintValidator<Telegram, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return isBlank(value) || isValidLink(value);
  }
}
