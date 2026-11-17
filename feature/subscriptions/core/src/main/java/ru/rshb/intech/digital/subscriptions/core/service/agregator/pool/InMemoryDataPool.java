package ru.rshb.intech.digital.subscriptions.core.service.agregator.pool;

import jakarta.annotation.Nonnull;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.lang3.StringUtils;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.model.DataItem;
import ru.rshb.intech.digital.subscriptions.entity.CategoryType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class InMemoryDataPool implements DataPool {

  private final MultiValuedMap<CategoryType, DataItem> cache;

  public InMemoryDataPool(@Nonnull List<DataItem> dataItems) {
    this.cache = new ArrayListValuedHashMap<>();
    dataItems.forEach(dataItem -> cache.put(dataItem.getCategoryType(), dataItem));
  }

  @Override
  public boolean isEmpty() {
    return cache.isEmpty();
  }

  @Override
  public long size() {
    return cache.size();
  }

  @Nonnull
  @Override
  public List<DataItem> findByCategoryType(@Nonnull CategoryType categoryType) {
    return cache.get(categoryType)
        .stream()
        .toList();
  }

  @Nonnull
  @Override
  public List<DataItem> findByCategoryTitles(@Nonnull CategoryType categoryType, @Nonnull Collection<String> providedTitles) {

    return cache.get(categoryType)
        .stream()
        .filter(item -> CollectionUtils.isNotEmpty(item.getCategories()))
        .filter(item -> {
          final var itemCats = new HashSet<>(item.getCategories());
          final var providedCats = new HashSet<>(providedTitles);
          final var intersection = CollectionUtils.intersection(itemCats, providedCats);
          return !intersection.isEmpty();
        })
        .toList();
  }

  @Nonnull
  @Override
  public DataPool sliceBy(@Nonnull SlicingOptions options) {

    final var isPopular = CollectionUtils.isNotEmpty(options.getCategories(CategoryType.ALL));
    if (isPopular) {
      return limit(getAll(), options);
    }

    final var dataItems = CategoryType.specific().stream()
        .flatMap(cat -> findByCategoryTitles(cat, options.getCategoryTitles()).stream())
        .toList();

    return limit(dataItems, options);
  }

  @Nonnull
  private DataPool limit(@Nonnull List<DataItem> providedDataItems, @Nonnull SlicingOptions options) {

    final MultiValuedMap<CategoryType, DataItem> dataItemsByCats = new ArrayListValuedHashMap<>();

    // 1. Разбиваем на подмножества по категориям
    for (final var dataItem : providedDataItems) {
      dataItemsByCats.put(dataItem.getCategoryType(), dataItem);
    }

    // 2. Каждое подмножество сортируем и ограничиваем в соответствии с опциями.
    // 3. Затем объединяем подмножества в одно.
    final var dataItems = new ArrayList<DataItem>();
    for (final var cat : dataItemsByCats.keySet()) {
      final var catOptions = options.getOptions(cat);
      final var catItems = dataItemsByCats.get(cat)
          .stream()
          .sorted(catOptions.getComparator())
          .distinct()
          .limit(catOptions.getMaxItemsNumber())
          .toList();
      dataItems.addAll(catItems);
    }

    return new InMemoryDataPool(dataItems);
  }

  @Nonnull
  private List<DataItem> getAll() {
    return new ArrayList<>(cache.values());
  }

  @Override
  public String toString() {
    final var sb = new StringBuilder("Digital Pool:\n\n");
    for (final var catType : CategoryType.values()) {
      final var items = findByCategoryType(catType);
      sb.append(catType).append(StringUtils.LF);
      for (final var item : items) {
        sb.append("\t")
            .append(item.getPublicationDateFormatted()).append(StringUtils.SPACE)
            .append(item.getStartDateFormatted()).append(StringUtils.SPACE)
            .append(item.getTitle()).append(StringUtils.SPACE)
            .append(item.getCategories())
            .append(StringUtils.LF);
      }
    }
    return sb.append(StringUtils.LF).toString();
  }
}
