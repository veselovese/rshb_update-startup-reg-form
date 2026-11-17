package ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form;

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
import ru.rshb.intech.digital.content.provider.api.InnovationContentService;
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowProposalFormDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowProposal;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.WindowProposalService;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.attribute.DemandId;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.attribute.Description;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.attribute.Email;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.attribute.Phone;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.attribute.Representative;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.attribute.StartupLink;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.attribute.StartupName;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.notification.WindowProposalNotification;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class WindowProposalFormTest {

  @Mock
  private FormAttributeProvider formAttributeProvider;

  @Mock
  private CensorAnalyzer censorAnalyzer;

  @Mock
  private WindowProposalService windowProposalService;

  @Mock
  private WindowProposalNotification windowProposalNotification;

  @Mock
  private InnovationContentService innovationContentService;

  private WindowProposalForm windowProposalForm;
  private InnovationWindowProposalFormDto dto;
  private InnovationWindowProposal entity;

  @BeforeEach
  void setUp() {

    final var attributes = new HashMap<String, Attribute<InnovationWindowProposalFormDto, ?, InnovationWindowProposal, ?>>();
    attributes.put("windowProposalDemandId", new DemandId(innovationContentService));
    attributes.put("windowProposalDescription", new Description());
    attributes.put("windowProposalEmail", new Email());
    attributes.put("windowProposalPhone", new Phone());
    attributes.put("windowProposalRepresentative", new Representative());
    attributes.put("windowProposalStartupLink", new StartupLink());
    attributes.put("windowProposalStartupName", new StartupName());

    given(
        formAttributeProvider.<InnovationWindowProposalFormDto, InnovationWindowProposal>findByFormClass(any())
    ).willReturn(
        new ArrayList<>(attributes.values())
    );

    windowProposalForm = new WindowProposalForm(
        formAttributeProvider,
        censorAnalyzer,
        windowProposalService,
        windowProposalNotification
    );

    dto = new InnovationWindowProposalFormDto();
    entity = new InnovationWindowProposal();
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

    dto.setDemandCardId("123");
    dto.setDescription("Lorem ipsum dolor amit");
    dto.setEmail("test@test.te");
    dto.setPhone("+7 (999) 666-42-42");
    dto.setRepresentativeName("Иванов Иван Иванович");
    dto.setStartupLink("https://rshbdigital.ru");
    dto.setStartupName("Lorem Ipsum");

    given(censorAnalyzer.analyze(any()))
        .willReturn(PreCheckResult.OK);

    given(innovationContentService.isExistById(anyString()))
        .willReturn(true);

    final var savedProposal = new InnovationWindowProposal();
    savedProposal.setId(1L);

    given(
        windowProposalService.save(any())
    ).willReturn(savedProposal);

    given(
        windowProposalService.getFormAttachmentAllowedExtensions()
    ).willReturn(new String[]{"pdf"});

    final InnovationWindowProposal processedProposal = windowProposalForm.process(dto);

    assertThat(processedProposal)
        .isNotNull();
  }
}
