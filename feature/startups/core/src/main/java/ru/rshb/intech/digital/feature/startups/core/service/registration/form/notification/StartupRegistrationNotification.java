package ru.rshb.intech.digital.feature.startups.core.service.registration.form.notification;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;
import ru.rshb.intech.digital.notifications.api.NotificationServiceAdapter;
import ru.rshb.intech.digital.notifications.api.TemplatedMessage;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;
import ru.rshb.intech.digital.notifications.internal.service.TemplatedEmailSender;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class StartupRegistrationNotification implements NotificationServiceAdapter<StartupRegistration> {

  private final InternalNotificationService internalNotificationService;
  private final TemplatedEmailSender templatedEmailSender;

  @Async
  @Override
  public void send(@Nonnull StartupRegistration data, @Nonnull List<Attachment> attachments) {
    sendInternalNotification(data, attachments);
    sendApplicantNotification(data);
  }

  private void sendInternalNotification(@Nonnull StartupRegistration registration,
                                        @Nonnull List<Attachment> attachments) {

    internalNotificationService.send(
        new InternalNotificationMessage(registration, attachments)
    );
  }

  private void sendApplicantNotification(@Nonnull StartupRegistration registration) {
    templatedEmailSender.send(
        TemplatedMessage.builder()
            .template(new ApplicantNotificationMessage())
            .useDefaultSubject(true)
            .collate(true)
            .recipients(Set.of(registration.getEmail()))
            .build()
    );
  }
}
