package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.notification;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadRegistration;
import ru.rshb.intech.digital.notifications.api.NotificationServiceAdapter;
import ru.rshb.intech.digital.notifications.api.TemplatedMessage;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;
import ru.rshb.intech.digital.notifications.internal.service.TemplatedEmailSender;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AgrotechOlympiadRegistrationNotification
    implements NotificationServiceAdapter<AgrotechOlympiadRegistration> {

  private final InternalNotificationService internalNotificationService;
  private final TemplatedEmailSender templatedEmailSender;

  @Async
  @Override
  public void send(@Nonnull AgrotechOlympiadRegistration registration, @Nonnull List<Attachment> attachments) {
    sendInternalNotification(registration);
    sendApplicantNotification(registration);
  }

  private void sendInternalNotification(@Nonnull AgrotechOlympiadRegistration registration) {
    internalNotificationService.send(
        new InternalNotificationMessage(registration)
    );
  }

  private void sendApplicantNotification(@Nonnull AgrotechOlympiadRegistration registration) {
    templatedEmailSender.send(
        TemplatedMessage.builder()
            .collate(true)
            .recipients(Set.of(registration.getEmail()))
            .useDefaultSubject(true)
            .template(new ApplicantNotificationMessage())
            .build()
    );
  }
}
