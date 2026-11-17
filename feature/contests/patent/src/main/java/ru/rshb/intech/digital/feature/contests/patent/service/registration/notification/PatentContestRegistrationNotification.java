package ru.rshb.intech.digital.feature.contests.patent.service.registration.notification;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.contests.patent.entity.PatentContestRegistration;
import ru.rshb.intech.digital.notifications.api.NotificationServiceAdapter;
import ru.rshb.intech.digital.notifications.api.TemplatedMessage;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;
import ru.rshb.intech.digital.notifications.internal.service.TemplatedEmailSender;
import ru.rshb.intech.digital.references.cities.core.service.CityReferenceService;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PatentContestRegistrationNotification implements NotificationServiceAdapter<PatentContestRegistration> {

  private final InternalNotificationService internalNotificationService;
  private final TemplatedEmailSender templatedEmailSender;
  private final CityReferenceService cityReferenceService;

  @Async
  @Override
  public void send(@Nonnull PatentContestRegistration data, @Nonnull List<Attachment> attachments) {
    sendInternalNotification(data, attachments);
    sendApplicantNotification(data);
  }

  private void sendInternalNotification(@Nonnull PatentContestRegistration data, @Nonnull List<Attachment> attachments) {
    internalNotificationService.send(
        new InternalNotificationMessage(data, attachments, cityReferenceService)
    );
  }

  private void sendApplicantNotification(@Nonnull PatentContestRegistration registration) {
    templatedEmailSender.send(
        TemplatedMessage.builder()
            .recipients(Set.of(registration.getEmail()))
            .useDefaultSubject(true)
            .template(new ApplicantNotificationMessage())
            .collate(true)
            .build()
    );
  }
}
