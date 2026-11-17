package ru.rshb.intech.digital.feature.downloads.core.service.form.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.feature.downloads.core.exception.RequestedFileNotFound;
import ru.rshb.intech.digital.feature.downloads.core.service.FileDownloadService;
import ru.rshb.intech.digital.feature.downloads.core.service.form.FileRequestForm;
import ru.rshb.intech.digital.feature.downloads.dto.FileRequestDto;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;
import ru.rshb.intech.digital.subsystem.content.files.service.FileIndexService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FileIdTest {

  @Mock
  private FileDownloadService fileDownloadService;

  @Mock
  private FileIndexService fileIndexService;

  private FileId fileIdAttribute;

  @BeforeEach
  void setUp() {
    fileIdAttribute = new FileId(fileDownloadService, fileIndexService);
  }

  @Test
  void processOrder_thenReturn1() {
    assertThat(fileIdAttribute.processOrder())
        .isEqualTo(1);
  }

  @Test
  void givenNonExistingFile_process_thenException() {

    given(
        fileDownloadService.isFileExist(anyString())
    ).willReturn(false);

    final var dto = new FileRequestDto();
    dto.setFileId("some-file-id");

    final var formContext = new FormContext<>(FileRequestForm.TITLE_KEY, dto, new FileRequest());

    assertThrowsExactly(RequestedFileNotFound.class, () -> {
      fileIdAttribute.process(formContext);
    });
  }
}