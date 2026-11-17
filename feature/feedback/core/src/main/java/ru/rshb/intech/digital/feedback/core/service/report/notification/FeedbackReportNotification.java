package ru.rshb.intech.digital.feedback.core.service.report.notification;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;
import ru.rshb.intech.digital.notifications.api.NotificationServiceAdapter;
import ru.rshb.intech.digital.notifications.api.TemplatedMessage;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;
import ru.rshb.intech.digital.notifications.internal.service.TemplatedEmailSender;

import java.util.List;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
@RequiredArgsConstructor
public class FeedbackReportNotification implements NotificationServiceAdapter<FeedbackReport> {

  private final InternalNotificationService internalNotificationService;
  private final TemplatedEmailSender templatedEmailSender;


  @Async
  @Override
  public void send(@Nonnull FeedbackReport feedbackReport, @Nonnull List<Attachment> attachments) {

    sendInternalNotification(feedbackReport, attachments);

    if (isNotBlank(feedbackReport.getEmail())) {
      sendApplicantNotification(feedbackReport);
    }
  }

  private void sendInternalNotification(@Nonnull FeedbackReport feedbackReport, @Nonnull List<Attachment> attachments) {
    internalNotificationService.send(
        new InternalNotificationMessage(feedbackReport, attachments)
    );
  }

  private void sendApplicantNotification(@Nonnull FeedbackReport feedbackReport) {
    templatedEmailSender.send(
        TemplatedMessage.builder()
            .recipients(Set.of(feedbackReport.getEmail()))
            .template(new ReporterNotificationMessage())
            .useDefaultSubject(true)
            .collate(true)
            .build()
    );
  }
}
