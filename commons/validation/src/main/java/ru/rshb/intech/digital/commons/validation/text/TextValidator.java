package ru.rshb.intech.digital.commons.validation.text;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TextValidator implements ConstraintValidator<SizeAfterReduce, String> {

  private int min = 0;
  private int max = Integer.MAX_VALUE;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    final var reducedValue = TextValidationUtils.removeNonPrintableChars(value);
    if (reducedValue == null) {
      return true;
    }

    return (reducedValue.length() >= min && reducedValue.length() <= max);
  }

  @Override
  public void initialize(SizeAfterReduce constraintAnnotation) {
    this.min = constraintAnnotation.min();
    this.max = constraintAnnotation.max();
  }
}
