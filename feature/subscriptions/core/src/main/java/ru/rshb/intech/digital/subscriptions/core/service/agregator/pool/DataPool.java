package ru.rshb.intech.digital.subscriptions.core.service.agregator.pool;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.model.DataItem;
import ru.rshb.intech.digital.subscriptions.entity.CategoryType;

import java.util.Collection;
import java.util.List;

public interface DataPool {

  boolean isEmpty();

  long size();

  @Nonnull
  List<DataItem> findByCategoryType(@Nonnull CategoryType categoryType);

  @Nonnull
  List<DataItem> findByCategoryTitles(@Nonnull CategoryType categoryType, @Nonnull Collection<String> titles);

  @Nonnull
  DataPool sliceBy(@Nonnull SlicingOptions options);
}
