package ru.rshb.intech.digital.vacancies.core.service.candidate.notification;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.notifications.api.NotificationServiceAdapter;
import ru.rshb.intech.digital.notifications.api.TemplatedMessage;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;
import ru.rshb.intech.digital.notifications.internal.service.TemplatedEmailSender;
import ru.rshb.intech.digital.vacancies.entity.CandidateCv;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class VacancyCandidateNotification implements NotificationServiceAdapter<CandidateCv> {

  private final InternalNotificationService internalNotificationService;
  private final TemplatedEmailSender templatedEmailSender;

  @Async
  @Override
  public void send(@Nonnull CandidateCv cv, @Nonnull List<Attachment> attachments) {
    sendInternalNotification(cv, attachments);
    sendApplicantNotification(cv);
  }

  private void sendInternalNotification(@Nonnull CandidateCv cv, @Nonnull List<Attachment> attachments) {
    internalNotificationService.send(
        new InternalNotificationMessage(cv, attachments)
    );
  }

  private void sendApplicantNotification(@Nonnull CandidateCv cv) {
    templatedEmailSender.send(
        TemplatedMessage.builder()
            .recipients(Set.of(cv.getEmail()))
            .collate(true)
            .useDefaultSubject(true)
            .template(new ApplicantNotificationMessage())
            .build()
    );
  }
}
