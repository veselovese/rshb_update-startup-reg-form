package ru.rshb.intech.digital.content.provider.api;

import ru.rshb.intech.digital.content.model.ItemType;
import ru.rshb.intech.digital.content.model.Trend;

import jakarta.annotation.Nonnull;

public interface TrendContentService extends PublicationContentService<Trend> {

  @Nonnull
  @Override
  default ItemType getAssotiatedItemType() {
    return ItemType.TREND;
  }
}
