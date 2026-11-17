package ru.rshb.intech.digital.feedback.core.service.report.form;

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
import ru.rshb.intech.digital.feedback.core.FeedbackOptions;
import ru.rshb.intech.digital.feedback.core.dto.FeedbackFormData;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;
import ru.rshb.intech.digital.feedback.core.service.FeedbackService;
import ru.rshb.intech.digital.feedback.core.service.report.form.attribute.Email;
import ru.rshb.intech.digital.feedback.core.service.report.form.attribute.Feedback;
import ru.rshb.intech.digital.feedback.core.service.report.form.attribute.Phone;
import ru.rshb.intech.digital.feedback.core.service.report.form.attribute.ReporterName;
import ru.rshb.intech.digital.feedback.core.service.report.notification.FeedbackReportNotification;
import ru.rshb.intech.digital.messages.I18n;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FeedbackFormTest {

  @Mock
  private FormAttributeProvider formAttributeProvider;

  @Mock
  private CensorAnalyzer censorAnalyzer;

  @Mock
  private FeedbackOptions feedbackOptions;

  @Mock
  private FeedbackService feedbackService;

  @Mock
  private FeedbackReportNotification feedbackReportNotification;


  private FeedbackForm form;
  private FeedbackFormData dto;
  private FeedbackReport entity;


  @BeforeEach
  void setUp() {

    final var attributes = new HashMap<String, Attribute<FeedbackFormData, ?, FeedbackReport, ?>>();
    attributes.put("feedbackFormReporterName", new ReporterName());
    attributes.put("feedbackFormReport", new Feedback());
    attributes.put("feedbackFormEmail", new Email(feedbackService, feedbackOptions));
    attributes.put("feedbackFormPhone", new Phone());

    given(
        formAttributeProvider.<FeedbackFormData, FeedbackReport>findByFormClass(any())
    ).willReturn(
        new ArrayList<>(attributes.values())
    );

    entity = new FeedbackReport();
    form = new FeedbackForm(formAttributeProvider, censorAnalyzer, feedbackService, feedbackReportNotification);
  }

  @Test
  void getName() {

    final String formName = form.getName();

    assertThat(formName)
        .isNotNull()
        .isEqualTo(I18n.of(FeedbackForm.TITLE_KEY));
  }

  @Test
  void givenValid_process_thenSuccess() {

    dto = new FeedbackFormData(
        "Иванов Иван Иванович",
        "test@test.te",
        "+7 (999) 666-42-42",
        "Lorem ipsum dolor amet",
        new MockMultipartFile(
            "attachment",
            "cv.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            "content".getBytes(StandardCharsets.UTF_8)
        )
    );

    given(censorAnalyzer.analyze(any()))
        .willReturn(PreCheckResult.OK);

    given(
        feedbackService.save(any())
    ).willReturn(entity);

    given(
        feedbackService.getFormAttachmentAllowedExtensions()
    ).willReturn(new String[]{"pdf"});

    given(
        feedbackOptions.maxReportsCountPerInterval()
    ).willReturn(5);

    given(
        feedbackService.getReportsNumberByEmail(anyString())
    ).willReturn(0L);

    final FeedbackReport result = form.process(dto);

    assertThat(result)
        .isNotNull();
  }

  @Test
  void givenSuspicious_process_thenSuccess() {

    dto = new FeedbackFormData(
        "Иванов Иван Иванович",
        "test@test.te",
        "+7 (999) 666-42-42",
        "Lorem ipsum dolor amet",
        new MockMultipartFile(
            "attachment",
            "cv.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            "content".getBytes(StandardCharsets.UTF_8)
        )
    );

    given(censorAnalyzer.analyze(any()))
        .will(invocation -> dto.reporterName().equals(invocation.getArgument(0))
            ? PreCheckResult.SUSPICIOUS
            : PreCheckResult.OK);

    given(
        feedbackService.save(any())
    ).willReturn(entity);

    given(
        feedbackService.getFormAttachmentAllowedExtensions()
    ).willReturn(new String[]{"pdf"});

    given(
        feedbackOptions.maxReportsCountPerInterval()
    ).willReturn(5);

    given(
        feedbackService.getReportsNumberByEmail(anyString())
    ).willReturn(0L);

    final FeedbackReport result = form.process(dto);

    assertThat(result)
        .isNotNull();
  }
}