package ru.rshb.intech.digital.subscriptions.core.service.agregator.collector.mapper;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.model.DataItem;

import java.util.List;

public interface DataMapper<T> {

  @Nonnull
  DataItem dataItemForm(@Nonnull T entity);

  @Nonnull
  default List<DataItem> dataItemsFrom(@Nonnull List<T> entities) {
    return entities.stream()
        .map(this::dataItemForm)
        .toList();
  }
}
