package ru.rshb.intech.digital.content.provider.api;

import ru.rshb.intech.digital.content.model.ItemType;
import ru.rshb.intech.digital.content.model.Lecture;

import jakarta.annotation.Nonnull;

public interface LectureContentService extends PublicationContentService<Lecture> {

  @Nonnull
  @Override
  default ItemType getAssotiatedItemType() {
    return ItemType.LECTURE;
  }
}
