package ru.rshb.intech.digital.feature.downloads.core.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.commons.forms.exception.common.RequestNumberExceededException;
import ru.rshb.intech.digital.feature.downloads.core.repository.FileRequestRepository;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;
import ru.rshb.intech.digital.subsystem.content.files.service.FileIndexService;

import java.time.LocalDateTime;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeUtilz.dayEndTime;
import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeUtilz.dayStartTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileDownloadServiceImpl implements FileDownloadService {

  private final FileRequestRepository fileRequestRepository;
  private final FileIndexService fileIndexService;

  @Nonnull
  @Override
  @Transactional
  public FileRequest save(@Nonnull FileRequest fileRequest) {

    fileRequest.setSubmitDate(LocalDateTime.now());

    if (isDownloadLimitExceeded(fileRequest)) {
      throw new RequestNumberExceededException();
    }

    final var savedRequest = fileRequestRepository.save(fileRequest);
    log.info("New file download request has been saved [{}]", savedRequest.getFile().getTitle());

    return savedRequest;
  }

  @Override
  public boolean isFileExist(@Nonnull String fileId) {
    return fileIndexService.isFileExist(fileId);
  }

  private boolean isDownloadLimitExceeded(@Nonnull FileRequest fileRequest) {

    final var file = fileRequest.getFile();

    final var submitDate = fileRequest.getSubmitDate().toLocalDate();
    final var dayStart = dayStartTime(submitDate);
    final var dayEnd = dayEndTime(submitDate);

    final var existingFileRequestsCount = fileRequestRepository.countAllByFileIdAndPhoneAndSubmitDateIsBetween(
        file.getId(),
        fileRequest.getPhone(),
        dayStart, dayEnd
    );

    return (existingFileRequestsCount >= file.getDownloadLimit());
  }
}
