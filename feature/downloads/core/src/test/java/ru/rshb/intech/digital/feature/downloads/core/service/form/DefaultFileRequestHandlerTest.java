package ru.rshb.intech.digital.feature.downloads.core.service.form;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckResult;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.feature.downloads.core.service.FileDownloadService;
import ru.rshb.intech.digital.feature.downloads.dto.FileRequestDto;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultFileRequestHandlerTest {

  @Mock
  private FileDownloadService fileDownloadService;

  @Mock
  private DefaultFileRequestNotificator fileRequestNotificator;

  private FormContext<FileRequestDto, FileRequest> formContext;

  @BeforeEach
  public void setUp() {
    formContext = Mockito.mock(FormContext.class);
  }

  @Test
  void create() {
    FileRequestResultHandler resultHandler = new DefaultFileRequestHandler(fileDownloadService, fileRequestNotificator);
  }

  @Test
  void givenOk_handleResult_thenOk() {

    final var fileRequest = new FileRequest();

    given(
        fileDownloadService.save(any())
    ).willReturn(fileRequest);

    FileRequestResultHandler resultHandler = new DefaultFileRequestHandler(fileDownloadService, fileRequestNotificator);
    FileRequest formResult = resultHandler.handleResult(fileRequest, formContext);

    assertThat(formResult)
        .isNotNull();

    assertThat(formResult.getSuspicious())
        .isFalse();
  }

  @Test
  void givenSuspicious_handleResult_thenOk() {

    final var fileRequest = new FileRequest();

    when(formContext.is(PreCheckResult.SUSPICIOUS))
        .thenReturn(true);

    given(
        fileDownloadService.save(any())
    ).willReturn(fileRequest);

    FileRequestResultHandler resultHandler = new DefaultFileRequestHandler(fileDownloadService, fileRequestNotificator);
    FileRequest formResult = resultHandler.handleResult(fileRequest, formContext);

    assertThat(formResult)
        .isNotNull();

    assertThat(formResult.getSuspicious())
        .isTrue();

  }
}