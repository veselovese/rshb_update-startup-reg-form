package ru.rshb.intech.digital.subsystem.content.files.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.subsystem.content.files.entity.FileIndexEntry;
import ru.rshb.intech.digital.subsystem.content.files.exception.FileIndexEntryNotFoundException;
import ru.rshb.intech.digital.subsystem.content.files.repository.FileIndexRepository;

@Service
@RequiredArgsConstructor
public class FileIndexServiceImpl implements FileIndexService {

  private final FileIndexRepository fileIndexRepository;

  @Nonnull
  @Override
  public FileIndexEntry getFileByExternalId(@Nonnull String externalId) {
    return fileIndexRepository.findByExternalId(externalId)
        .orElseThrow(() -> new FileIndexEntryNotFoundException("File entry not found: " + externalId));
  }

  @Override
  public boolean isFileExist(@Nonnull String externalId) {
    return fileIndexRepository.existsByExternalIdIgnoreCase(externalId);
  }
}
