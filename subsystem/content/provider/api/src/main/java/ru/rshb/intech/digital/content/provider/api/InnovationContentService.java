package ru.rshb.intech.digital.content.provider.api;

import ru.rshb.intech.digital.content.model.Innovation;
import ru.rshb.intech.digital.content.model.ItemType;

import jakarta.annotation.Nonnull;

public interface InnovationContentService extends PublicationContentService<Innovation> {

  @Nonnull
  @Override
  default ItemType getAssotiatedItemType() {
    return ItemType.INNOVATION;
  }
}
