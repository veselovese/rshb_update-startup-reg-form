package ru.rshb.intech.digital.subsystem.content.index.service;

import ru.rshb.intech.digital.content.model.ItemType;
import ru.rshb.intech.digital.subsystem.content.index.entity.ContentIndexEntry;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public interface ContentIndexService {

  @Nullable
  ContentIndexEntry getById(@Nonnull String id);

  @Nonnull
  String putInIndex(@Nonnull String pubId, @Nonnull ItemType itemType);

}
