package ru.rshb.intech.digital.feature.downloads.core.service.form;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;

public interface FileRequestSaver {

  @Nonnull
  FileRequest save(@Nonnull FileRequest fileRequest);

}
