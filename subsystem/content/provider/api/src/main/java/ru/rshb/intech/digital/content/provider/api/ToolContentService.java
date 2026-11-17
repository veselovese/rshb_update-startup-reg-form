package ru.rshb.intech.digital.content.provider.api;

import ru.rshb.intech.digital.content.model.ItemType;
import ru.rshb.intech.digital.content.model.Tool;

import jakarta.annotation.Nonnull;

public interface ToolContentService extends PublicationContentService<Tool> {

  @Nonnull
  @Override
  default ItemType getAssotiatedItemType() {
    return ItemType.TOOL;
  }
}
