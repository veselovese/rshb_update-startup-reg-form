package ru.rshb.intech.digital.feature.internship.core.service.feedback.form.notification;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipFeedback;
import ru.rshb.intech.digital.notifications.api.NotificationServiceAdapter;
import ru.rshb.intech.digital.notifications.api.TemplatedMessage;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;
import ru.rshb.intech.digital.notifications.internal.service.TemplatedEmailSender;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class InternshipFeedbackNotification implements NotificationServiceAdapter<InternshipFeedback> {

  private final InternalNotificationService internalNotificationService;
  private final TemplatedEmailSender templatedEmailSender;

  @Async
  @Override
  public void send(@Nonnull InternshipFeedback data, @Nonnull List<Attachment> attachments) {
    sendInternalNotification(data, attachments);
    sendApplicantNotification(data);
  }

  private void sendInternalNotification(@Nonnull InternshipFeedback feedback, @Nonnull List<Attachment> attachments) {
    internalNotificationService.send(
        new InternshipFeedbackInternalMsg(feedback, attachments)
    );
  }

  private void sendApplicantNotification(@Nonnull InternshipFeedback feedback) {
    templatedEmailSender.send(
        TemplatedMessage.builder()
            .recipients(Set.of(feedback.getEmail()))
            .collate(true)
            .useDefaultSubject(true)
            .template(new InternshipFeedbackApplicantMsg())
            .build()
    );
  }
}
