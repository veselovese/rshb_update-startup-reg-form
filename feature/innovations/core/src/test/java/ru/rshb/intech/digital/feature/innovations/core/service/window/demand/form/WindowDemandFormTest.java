package ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form;

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
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowDemandFormDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowDemand;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.WindowDemandService;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.attribute.Company;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.attribute.Description;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.attribute.Email;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.attribute.Phone;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.attribute.Site;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.attribute.Summary;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.notification.WindowDemandNotification;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class WindowDemandFormTest {

  @Mock
  private FormAttributeProvider formAttributeProvider;

  @Mock
  private CensorAnalyzer censorAnalyzer;

  @Mock
  private WindowDemandService windowDemandService;

  @Mock
  private WindowDemandNotification windowDemandNotification;

  private WindowDemandForm windowDemandForm;
  private InnovationWindowDemandFormDto dto;
  private InnovationWindowDemand entity;


  @BeforeEach
  void setUp() {

    final var attributes = new HashMap<String, Attribute<InnovationWindowDemandFormDto, ?, InnovationWindowDemand, ?>>();
    attributes.put("windowDemandCompany", new Company());
    attributes.put("windowDemandDescription", new Description());
    attributes.put("windowDemandEmail", new Email());
    attributes.put("windowDemandPhone", new Phone());
    attributes.put("windowDemandSite", new Site());
    attributes.put("windowDemandSummary", new Summary());

    given(
        formAttributeProvider.<InnovationWindowDemandFormDto, InnovationWindowDemand>findByFormClass(any())
    ).willReturn(
        new ArrayList<>(attributes.values())
    );

    windowDemandForm = new WindowDemandForm(
        formAttributeProvider,
        censorAnalyzer,
        windowDemandService,
        windowDemandNotification
    );

    dto = new InnovationWindowDemandFormDto();
    entity = new InnovationWindowDemand();
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

    dto.setCompany("Lorem Ipsum");
    dto.setDescription("Lorem ipsum dolor amit");
    dto.setPhone("+7 (999) 666-42-42");
    dto.setEmail("test@test.te");
    dto.setSite("https://rshbdigital.ru");
    dto.setSummary("Lorem ipsum dolor");

    given(censorAnalyzer.analyze(any()))
        .willReturn(PreCheckResult.OK);

    final var savedDemand = new InnovationWindowDemand();
    savedDemand.setId(1L);

    given(
        windowDemandService.save(any())
    ).willReturn(savedDemand);

    given(
        windowDemandService.getFormAttachmentAllowedExtensions()
    ).willReturn(new String[]{"pdf"});

    final InnovationWindowDemand processedDemand = windowDemandForm.process(dto);

    assertThat(processedDemand)
        .isNotNull();
  }
}
