package ru.rshb.intech.digital.subsystem.content.files.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.subsystem.content.files.entity.FileIndexEntry;

public interface FileIndexService {

  @Nonnull
  FileIndexEntry getFileByExternalId(@Nonnull String externalId);

  boolean isFileExist(@Nonnull String externalId);
}
