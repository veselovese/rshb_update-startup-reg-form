package ru.rshb.intech.digital.content.provider.api;

import ru.rshb.intech.digital.content.model.ItemType;
import ru.rshb.intech.digital.content.model.Technology;

import jakarta.annotation.Nonnull;

public interface TechnologyContentService extends PublicationContentService<Technology> {

  @Nonnull
  @Override
  default ItemType getAssotiatedItemType() {
    return ItemType.TECHNOLOGY;
  }
}
