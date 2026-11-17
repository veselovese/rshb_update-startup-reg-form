package ru.rshb.intech.digital.commons.validation.url;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;


public class UrlValidator implements ConstraintValidator<Url, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return StringUtils.isBlank(value) || UrlUtils.isValidUrl(value);
  }
}
