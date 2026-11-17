package ru.rshb.intech.digital.subscriptions.core.service.category;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionCategoriesData;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionCategory;

import java.util.List;
import java.util.Set;

public interface SubscriptionCategoryService {

  @Nonnull
  List<SubscriptionCategory> findAllCategories();

  @Nonnull
  Set<SubscriptionCategory> findUnknownCategories(@Nonnull Set<SubscriptionCategory> providedCategories);

  @Nonnull
  SubscriptionCategoriesData findCategories(@Nonnull SubscriptionCategoriesData providedCategories);

  @Nonnull
  SubscriptionCategoriesData findPopularCategories();
}
