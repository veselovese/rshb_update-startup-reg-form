package ru.rshb.intech.digital.subscriptions.dto.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.rshb.intech.digital.subscriptions.dto.SubscriptionCategoriesRequest;

public class SubscriptionCategoryValidator
    implements ConstraintValidator<SubscriptionCategory, SubscriptionCategoriesRequest> {

  @Override
  public boolean isValid(SubscriptionCategoriesRequest value, ConstraintValidatorContext context) {

    if (value == null) {
      return false;
    }

    if (value.isPopular()) {
      return true;
    }

    return !value.isEmpty();
  }
}
