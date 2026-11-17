package ru.rshb.intech.digital.feedback.core.service.report.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;
import ru.rshb.intech.digital.feedback.core.repository.FeedbackReportRepository;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;
import ru.rshb.intech.digital.notifications.internal.service.TemplatedEmailSender;

import java.nio.charset.StandardCharsets;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class FeedbackReportNotificationTest {

  @Mock
  private InternalNotificationService internalNotificationService;

  @Mock
  private TemplatedEmailSender templatedEmailSender;

  @Mock
  private FeedbackReportRepository feedbackReportRepository;

  @InjectMocks
  private FeedbackReportNotification feedbackReportNotification;

  @BeforeEach
  void setUp() {
  }

  @Test
  void givenNonBlankEmail_send_thenSuccess() {

    final var feedbackReport = new FeedbackReport();
    feedbackReport.setEmail("test@test.te");

    final var attachment = Attachment.fromMultipart(
        new MockMultipartFile(
            "attachment",
            "cv.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            "content".getBytes(StandardCharsets.UTF_8)
        ),
        "pdf"
    );

    feedbackReportNotification.send(feedbackReport, List.of(attachment));
  }

  @Test
  void givenBlankEmail_send_thenSuccess() {

    final var feedbackReport = new FeedbackReport();

    final var attachment = Attachment.fromMultipart(
        new MockMultipartFile(
            "attachment",
            "cv.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            "content".getBytes(StandardCharsets.UTF_8)
        ),
        "pdf"
    );

    feedbackReportNotification.send(feedbackReport, List.of(attachment));
  }
}