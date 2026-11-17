package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.service.notification;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadQuestion;
import ru.rshb.intech.digital.notifications.api.NotificationServiceAdapter;
import ru.rshb.intech.digital.notifications.api.TemplatedMessage;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;
import ru.rshb.intech.digital.notifications.internal.service.TemplatedEmailSender;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AgrotechOlympiadNotification implements NotificationServiceAdapter<AgrotechOlympiadQuestion> {

  private final InternalNotificationService internalNotificationService;
  private final TemplatedEmailSender templatedEmailSender;

  @Async
  @Override
  public void send(@Nonnull AgrotechOlympiadQuestion question, @Nonnull List<Attachment> attachments) {
    sendInternalNotification(question);
    sendApplicantNotification(question);
  }

  private void sendInternalNotification(@Nonnull AgrotechOlympiadQuestion question) {

    internalNotificationService.send(
        new InternalNotificationMessage(question)
    );
  }

  private void sendApplicantNotification(@Nonnull AgrotechOlympiadQuestion question) {
    templatedEmailSender.send(
        TemplatedMessage.builder()
            .recipients(Set.of(question.getEmail()))
            .collate(true)
            .useDefaultSubject(true)
            .template(new ApplicantNotificationMessage())
            .build()
    );
  }
}
