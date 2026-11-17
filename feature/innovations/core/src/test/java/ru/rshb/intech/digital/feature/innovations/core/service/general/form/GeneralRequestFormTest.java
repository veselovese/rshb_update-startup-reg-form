package ru.rshb.intech.digital.feature.innovations.core.service.general.form;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import ru.rshb.intech.digital.censor.forms.CensorAnalyzer;
import ru.rshb.intech.digital.commons.forms.attributes.Attribute;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckResult;
import ru.rshb.intech.digital.commons.forms.attributes.provider.FormAttributeProvider;
import ru.rshb.intech.digital.feature.innovations.core.dto.GeneralRequestDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.GeneralRequest;
import ru.rshb.intech.digital.feature.innovations.core.service.general.GeneralRequestService;
import ru.rshb.intech.digital.feature.innovations.core.service.general.form.attribute.ApplicantName;
import ru.rshb.intech.digital.feature.innovations.core.service.general.form.attribute.Email;
import ru.rshb.intech.digital.feature.innovations.core.service.general.form.attribute.Phone;
import ru.rshb.intech.digital.feature.innovations.core.service.general.form.attribute.Proposal;
import ru.rshb.intech.digital.feature.innovations.core.service.general.notification.GeneralRequestNotification;
import ru.rshb.intech.digital.messages.I18n;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GeneralRequestFormTest {

  @Mock
  private FormAttributeProvider formAttributeProvider;

  @Mock
  private GeneralRequestService generalRequestService;

  @Mock
  private GeneralRequestNotification generalRequestNotification;

  @Mock
  private CensorAnalyzer censorAnalyzer;

  private GeneralRequestForm generalRequestForm;
  private GeneralRequestDto dto;
  private GeneralRequest entity;

  @BeforeEach
  void setUp() {

    final var attributes = new HashMap<String, Attribute<GeneralRequestDto, ?, GeneralRequest, ?>>();
    attributes.put("innovationsGeneralRequestFormApplicantName", new ApplicantName());
    attributes.put("innovationsGeneralRequestFormEmail", new Email());
    attributes.put("innovationsGeneralRequestFormPhone", new Phone(generalRequestService));
    attributes.put("innovationsGeneralRequestFormProposal", new Proposal());

    given(
        formAttributeProvider.<GeneralRequestDto, GeneralRequest>findByFormClass(any())
    ).willReturn(
        new ArrayList<>(attributes.values())
    );

    generalRequestForm = new GeneralRequestForm(
        formAttributeProvider,
        generalRequestService,
        generalRequestNotification,
        censorAnalyzer
    );

    dto = new GeneralRequestDto();
    entity = new GeneralRequest();
  }

  @Test
  void getName() {

    final String formName = generalRequestForm.getName();

    assertThat(formName)
        .isNotNull()
        .isEqualTo(I18n.of(GeneralRequestForm.TITLE_KEY));
  }

  @Test
  void givenValid_process_thenSuccess() {

    dto.setAttachment(
        new MockMultipartFile(
            "attachment",
            "cv.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            "content".getBytes(StandardCharsets.UTF_8)
        )
    );
    dto.setApplicantName("Иванов Иван Иванович");
    dto.setPhone("+7 (999) 666-42-42");
    dto.setEmail("test@test.te");
    dto.setProposal("Lorem ipsum dolor amit");

    given(censorAnalyzer.analyze(any()))
        .willReturn(PreCheckResult.OK);

    given(generalRequestService.isRequestExist(anyString()))
        .willReturn(false);

    given(
        generalRequestService.getFormAttachmentAllowedExtensions()
    ).willReturn(new String[]{"pdf"});

    final var savedRegistration = new GeneralRequest();
    savedRegistration.setId(1L);

    given(
        generalRequestService.save(any())
    ).willReturn(savedRegistration);

    final GeneralRequest processedReq = generalRequestForm.process(dto);

    assertThat(processedReq)
        .isNotNull();
  }
}
