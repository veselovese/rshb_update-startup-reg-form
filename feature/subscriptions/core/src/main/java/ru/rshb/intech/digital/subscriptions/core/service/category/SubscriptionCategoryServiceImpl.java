package ru.rshb.intech.digital.subscriptions.core.service.category;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.subscriptions.core.repository.SubscriptionCategoryRepository;
import ru.rshb.intech.digital.subscriptions.entity.CategoryType;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionCategoriesData;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionCategory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionCategoryServiceImpl implements SubscriptionCategoryService {

  private final SubscriptionCategoryRepository categoryRepository;


  @Nonnull
  @Override
  @Transactional
  public List<SubscriptionCategory> findAllCategories() {
    return categoryRepository.findAllCategories();
  }

  @Nonnull
  @Override
  public Set<SubscriptionCategory> findUnknownCategories(@Nonnull Set<SubscriptionCategory> providedCategories) {

    final Set<String> categoryIds = findAllCategories().stream()
        .map(SubscriptionCategory::getExternalId)
        .collect(Collectors.toSet());

    return providedCategories
        .stream()
        .filter(cat -> !categoryIds.contains(cat.getExternalId()))
        .collect(Collectors.toSet());
  }

  @Nonnull
  @Override
  public SubscriptionCategoriesData findCategories(@Nonnull SubscriptionCategoriesData providedCategories) {

    final var strictTypeFilteredCategories = new HashSet<SubscriptionCategory>();

    for (final var type : CategoryType.values()) {
      final var externalIds = providedCategories.getExternalIds(type);
      final var categories = categoryRepository.findAllByExternalIds(type, externalIds);
      strictTypeFilteredCategories.addAll(categories);
    }

    return new SubscriptionCategoriesData(providedCategories.isPopular(), strictTypeFilteredCategories);
  }

  @Nonnull
  @Override
  public SubscriptionCategoriesData findPopularCategories() {
    final var popularCategory = categoryRepository.getPopularCategory();
    return new SubscriptionCategoriesData(true, Set.of(popularCategory));
  }
}
