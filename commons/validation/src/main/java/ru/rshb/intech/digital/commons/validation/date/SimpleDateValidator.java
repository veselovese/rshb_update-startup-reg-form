package ru.rshb.intech.digital.commons.validation.date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeUtilz.isSimpleDate;

public class SimpleDateValidator implements ConstraintValidator<SimpleDate, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return isBlank(value) || isSimpleDate(value);
  }
}
