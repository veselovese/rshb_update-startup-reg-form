package ru.rshb.intech.digital.subscriptions.core.validation;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.subscriptions.core.exception.SubscriptionValidationException;
import ru.rshb.intech.digital.subscriptions.core.repository.BannedEmailRegistry;
import ru.rshb.intech.digital.subscriptions.core.service.category.SubscriptionCategoryService;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionRequestData;

@Component
@RequiredArgsConstructor
public class SubscriptionDataValidator {

  private final SubscriptionCategoryService subscriptionCategoryService;
  private final BannedEmailRegistry bannedEmailRegistry;


  @Nonnull
  public SubscriptionRequestData validate(@Nonnull SubscriptionRequestData model) {

    final var categories = model.getCategoriesData().getCategories();

    final boolean isCustomSelectedCategories = (!model.getCategoriesData().isPopular() && categories.isEmpty());
    if (isCustomSelectedCategories) {
      throw SubscriptionValidationException.categoriesMissing();
    }

    final var email = model.getEmail();
    if (bannedEmailRegistry.existsByEmailAndDeletedFalse(email)) {
      throw SubscriptionValidationException.emailBanned(email);
    }

    final var unknownCategories = subscriptionCategoryService.findUnknownCategories(categories);
    if (!unknownCategories.isEmpty()) {
      throw SubscriptionValidationException.unknownCategories();
    }

    return model;
  }
}
