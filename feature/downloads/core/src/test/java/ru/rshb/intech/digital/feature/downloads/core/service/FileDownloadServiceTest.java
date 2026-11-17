package ru.rshb.intech.digital.feature.downloads.core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.exception.common.RequestNumberExceededException;
import ru.rshb.intech.digital.feature.downloads.core.repository.FileRequestRepository;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;
import ru.rshb.intech.digital.subsystem.content.files.entity.FileIndexEntry;
import ru.rshb.intech.digital.subsystem.content.files.service.FileIndexService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FileDownloadServiceTest {

  @Mock
  private FileRequestRepository fileRequestRepository;

  @Mock
  private FileIndexService fileIndexService;

  private FileDownloadService fileDownloadService;

  @BeforeEach
  void setUp() {
    fileDownloadService = new FileDownloadServiceImpl(fileRequestRepository, fileIndexService);
  }

  @Test
  void givenValid_save_thenReturnRequest() {

    final var file = new FileIndexEntry();
    file.setId(1L);
    file.setDownloadLimit(2);

    final var fileRequest = new FileRequest();
    fileRequest.setPhone("+79996664242");
    fileRequest.setFile(file);

    given(
        fileRequestRepository.save(any())
    ).willReturn(fileRequest);

    given(
        fileRequestRepository.countAllByFileIdAndPhoneAndSubmitDateIsBetween(anyLong(), anyString(), any(), any())
    ).willReturn(0L);

    final var savedRequest = fileDownloadService.save(fileRequest);

    assertThat(savedRequest.getSubmitDate())
        .isNotNull();
  }

  @Test
  void givenEqualMaxDownloads_save_thenDownloadLimitError() {

    final int downloadLimit = 2;

    final var file = new FileIndexEntry();
    file.setId(1L);
    file.setDownloadLimit(downloadLimit);

    final var fileRequest = new FileRequest();
    fileRequest.setPhone("+79996664242");
    fileRequest.setFile(file);

    given(
        fileRequestRepository.countAllByFileIdAndPhoneAndSubmitDateIsBetween(anyLong(), anyString(), any(), any())
    ).willReturn((long) downloadLimit);

    final var exception = assertThrowsExactly(RequestNumberExceededException.class, () -> {
      fileDownloadService.save(fileRequest);
    });
  }

  @Test
  void givenGreaterThanMaxDownloads_save_thenDownloadLimitError() {

    final int downloadLimit = 2;

    final var file = new FileIndexEntry();
    file.setId(1L);
    file.setDownloadLimit(downloadLimit);

    final var fileRequest = new FileRequest();
    fileRequest.setPhone("+79996664242");
    fileRequest.setFile(file);

    given(
        fileRequestRepository.countAllByFileIdAndPhoneAndSubmitDateIsBetween(anyLong(), anyString(), any(), any())
    ).willReturn((long) downloadLimit + 1);

    final var exception = assertThrowsExactly(RequestNumberExceededException.class, () -> {
      fileDownloadService.save(fileRequest);
    });
  }

  @Test
  void isFileExist() {

    final var fileId = "some-file-id";

    given(
        fileIndexService.isFileExist(fileId)
    ).willReturn(true);

    assertThat(fileDownloadService.isFileExist(fileId))
        .isTrue();
  }
}