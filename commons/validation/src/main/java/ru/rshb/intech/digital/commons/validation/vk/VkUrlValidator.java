package ru.rshb.intech.digital.commons.validation.vk;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class VkUrlValidator implements ConstraintValidator<VkUrl, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return isBlank(value) || VkUrlUtils.isValidVkUrl(value);
  }
}
