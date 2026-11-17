package ru.rshb.intech.digital.commons.validation.phone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return isBlank(value) || PhoneUtils.getPhone(value)
        .isPresent();
  }
}
