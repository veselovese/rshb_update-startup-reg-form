package ru.rshb.intech.digital.feature.downloads.core.service.form;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.censor.forms.CensorAnalyzer;
import ru.rshb.intech.digital.commons.forms.attributes.Attribute;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckResult;
import ru.rshb.intech.digital.commons.forms.attributes.provider.FormAttributeProvider;
import ru.rshb.intech.digital.feature.downloads.core.service.FileDownloadService;
import ru.rshb.intech.digital.feature.downloads.core.service.form.attribute.Email;
import ru.rshb.intech.digital.feature.downloads.core.service.form.attribute.FileId;
import ru.rshb.intech.digital.feature.downloads.core.service.form.attribute.Name;
import ru.rshb.intech.digital.feature.downloads.core.service.form.attribute.Organization;
import ru.rshb.intech.digital.feature.downloads.core.service.form.attribute.Phone;
import ru.rshb.intech.digital.feature.downloads.core.service.form.attribute.Position;
import ru.rshb.intech.digital.feature.downloads.dto.FileRequestDto;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.subsystem.content.files.service.FileIndexService;

import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FileRequestFormTest {

  @Mock
  private DefaultFileRequestNotificator defaultFileRequestNotificator;

  @Mock
  private FormAttributeProvider formAttributeProvider;

  @Mock
  private CensorAnalyzer censorAnalyzer;

  @Mock
  private FileDownloadService fileDownloadService;

  @Mock
  private FileIndexService fileIndexService;

  @Mock
  private FileRequestResultHandler customFormResultHandler;

  private FileRequestForm fileRequestForm;


  private FileRequestDto dto;
  private FileRequest entity;

  @BeforeEach
  void setUp() {

    final var attributes = new HashMap<String, Attribute<FileRequestDto, ?, FileRequest, ?>>();
    attributes.put("fileRequestFormFileId", new FileId(fileDownloadService, fileIndexService));
    attributes.put("fileRequestFormUserName", new Name());
    attributes.put("fileRequestFormEmail", new Email());
    attributes.put("fileRequestFormPhone", new Phone());
    attributes.put("fileRequestFormPosition", new Position());
    attributes.put("fileRequestFormOrganization", new Organization());

    given(
        formAttributeProvider.<FileRequestDto, FileRequest>findByFormClass(any())
    ).willReturn(
        new ArrayList<>(attributes.values())
    );

    fileRequestForm = new FileRequestForm(
        formAttributeProvider,
        censorAnalyzer,
        new DefaultFileRequestHandler(fileDownloadService, defaultFileRequestNotificator)
    );

    dto = new FileRequestDto();
    entity = new FileRequest();
  }

  @Test
  void getName() {

    final String formName = fileRequestForm.getName();

    assertThat(formName)
        .isNotNull()
        .isEqualTo(I18n.of(FileRequestForm.TITLE_KEY));
  }

  @Test
  void givenValid_process_thenSuccess() {

    dto.setFileId("some-file-id");
    dto.setName("Иванов Иван Иванович");
    dto.setPhone("+7 (999) 666-42-42");
    dto.setEmail("test@test.te");
    dto.setPosition("Генеральный директор");
    dto.setOrganization("ООО \"Керосин\"");

    given(
        censorAnalyzer.analyze(any())
    ).willReturn(PreCheckResult.OK);

    given(
        fileDownloadService.isFileExist(anyString())
    ).willReturn(true);

    given(
        fileDownloadService.save(any())
    ).willReturn(new FileRequest());

    final FileRequest fileRequest = fileRequestForm.process(dto);

    assertThat(fileRequest)
        .isNotNull();
  }

  @Test
  void givenValid_process_result_handler_thenSuccess() {

    dto.setFileId("some-file-id");
    dto.setName("Иванов Иван Иванович");
    dto.setPhone("+7 (999) 666-42-42");
    dto.setEmail("test@test.te");
    dto.setPosition("Генеральный директор");
    dto.setOrganization("ООО \"Керосин\"");

    given(
        censorAnalyzer.analyze(any())
    ).willReturn(PreCheckResult.OK);

    given(
        fileDownloadService.isFileExist(anyString())
    ).willReturn(true);

    given(
        customFormResultHandler.handleResult(any(), any())
    ).willReturn(new FileRequest());

    final FileRequest fileRequest = fileRequestForm.process(dto, customFormResultHandler);

    assertThat(fileRequest)
        .isNotNull();
  }
}