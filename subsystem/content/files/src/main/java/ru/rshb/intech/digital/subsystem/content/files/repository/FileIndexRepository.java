package ru.rshb.intech.digital.subsystem.content.files.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.subsystem.content.files.entity.FileIndexEntry;

import java.util.Optional;

public interface FileIndexRepository extends JpaRepository<FileIndexEntry, Long> {

  @Nonnull
  Optional<FileIndexEntry> findByExternalId(@Nonnull String externalId);

  boolean existsByExternalIdIgnoreCase(@Nonnull String externalId);

}
