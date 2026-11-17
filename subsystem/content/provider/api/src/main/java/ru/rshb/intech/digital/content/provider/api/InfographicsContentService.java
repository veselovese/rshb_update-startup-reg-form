package ru.rshb.intech.digital.content.provider.api;

import ru.rshb.intech.digital.content.model.Infographic;
import ru.rshb.intech.digital.content.model.ItemType;

import jakarta.annotation.Nonnull;

public interface InfographicsContentService extends PublicationContentService<Infographic> {

  @Nonnull
  @Override
  default ItemType getAssotiatedItemType() {
    return ItemType.INFOGRAPHIC;
  }
}
