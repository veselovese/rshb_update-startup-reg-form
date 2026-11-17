package ru.rshb.intech.digital.subscriptions.core.service.agregator.pool;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.model.DataItem;
import ru.rshb.intech.digital.subscriptions.entity.CategoryType;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionCategory;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SlicingOptions {

  private final Map<CategoryType, CategoryOptions> optionsMap = new HashMap<>();
  private final MultiValuedMap<CategoryType, SubscriptionCategory> categoriesMap = new ArrayListValuedHashMap<>();

  @Nonnull
  public static SlicingOptions create() {
    return new SlicingOptions();
  }

  @Nonnull
  public SlicingOptions setOptions(@Nonnull CategoryType categoryType, CategoryOptions options) {
    optionsMap.put(categoryType, options);
    return this;
  }

  public CategoryOptions getOptions(@Nonnull CategoryType categoryType) {
    return optionsMap.get(categoryType);
  }

  public Collection<SubscriptionCategory> getCategories(@Nonnull CategoryType categoryType) {
    return categoriesMap.get(categoryType);
  }

  public Collection<SubscriptionCategory> getCategories() {
    return categoriesMap.values();
  }

  @Nonnull
  public SlicingOptions setCategories(@Nonnull Collection<SubscriptionCategory> categories) {
    for (final var cat : categories) {
      categoriesMap.put(cat.getType(), cat);
    }
    return this;
  }

  @Nonnull
  public Set<String> getCategoryTitles() {
    return getCategories().stream()
        .map(SubscriptionCategory::getTitle)
        .collect(Collectors.toSet());
  }

  @Getter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CategoryOptions {

    private long minItemsNumber;
    private long maxItemsNumber;
    private Comparator<DataItem> comparator;
  }
}
