package ru.rshb.intech.digital.feature.downloads.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;

import java.time.LocalDateTime;

public interface FileRequestRepository extends JpaRepository<FileRequest, Long> {

  long countAllByFileIdAndPhoneAndSubmitDateIsBetween(
      @Nonnull Long fileId,
      @Nonnull String phone,
      @Nonnull LocalDateTime start,
      @Nonnull LocalDateTime end
  );
}
