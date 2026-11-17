package ru.rshb.intech.digital.feedback.core.service.report.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.commons.utils.attachment.Attachments;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InternalNotificationMessageTest {

  private FeedbackReport feedbackReport;
  private InternalNotificationMessage internalNotificationMessage;

  @BeforeEach
  void setUp() {

    feedbackReport = new FeedbackReport();

    final var attachments = Attachments.list()
        .ofNullable(
            new MockMultipartFile(
                "attachment",
                "cv.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                "content".getBytes(StandardCharsets.UTF_8)
            ),
            new String[] {"pdf"}
        ).collectNonNull();

    internalNotificationMessage = new InternalNotificationMessage(feedbackReport, attachments);
  }

  @Test
  void getTopic() {
    assertThat(
        internalNotificationMessage.getTopic()
    ).isNotBlank();
  }

  @Test
  void givenOk_getSubject_thenSubject() {

    feedbackReport.setSubmitDate(LocalDateTime.now());
    feedbackReport.setSuspicious(false);

    final String subject = internalNotificationMessage.getSubject();

    assertThat(subject)
        .startsWithIgnoringCase("Обратная связь:");
  }

  @Test
  void givenSuspicious_getSubject_thenSubjectWithPrefix() {

    feedbackReport.setSubmitDate(LocalDateTime.now());
    feedbackReport.setSuspicious(true);

    final String subject = internalNotificationMessage.getSubject();

    assertThat(subject)
        .startsWithIgnoringCase("[Подозрительно]");
  }

  @Test
  void isUseDefaultSubject() {
    assertThat(
        internalNotificationMessage.isUseDefaultSubject()
    ).isFalse();
  }

  @Test
  void getValues() {

    feedbackReport.setSubmitDate(LocalDateTime.now());

    final Values values = internalNotificationMessage.getValues();

    assertThat(values)
        .isNotNull();
  }

  @Test
  void getAttachments() {

    final List<Attachment> attachments = internalNotificationMessage.getAttachments();

    assertThat(attachments)
        .isNotNull();
  }
}