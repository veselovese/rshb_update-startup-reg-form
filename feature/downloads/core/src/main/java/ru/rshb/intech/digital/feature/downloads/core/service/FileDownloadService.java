package ru.rshb.intech.digital.feature.downloads.core.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.downloads.core.service.form.FileRequestSaver;

public interface FileDownloadService extends FileRequestSaver {

  boolean isFileExist(@Nonnull String fileId);
}
