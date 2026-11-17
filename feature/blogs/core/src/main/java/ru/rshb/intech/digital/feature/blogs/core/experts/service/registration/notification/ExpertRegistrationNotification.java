package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.notification;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.notifications.api.DefaultAnswerMessageTemplate;
import ru.rshb.intech.digital.notifications.api.NotificationServiceAdapter;
import ru.rshb.intech.digital.notifications.api.TemplatedMessage;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;
import ru.rshb.intech.digital.notifications.internal.service.TemplatedEmailSender;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ExpertRegistrationNotification implements NotificationServiceAdapter<ExpertRegistration> {

  private final InternalNotificationService internalNotificationService;
  private final TemplatedEmailSender templatedEmailSender;

  @Async
  @Override
  public void send(@Nonnull ExpertRegistration registration, @Nonnull List<Attachment> attachments) {
    sendInternalNotification(registration, attachments);
    sendApplicantNotification(registration);
  }

  private void sendInternalNotification(@Nonnull ExpertRegistration registration,
                                        @Nonnull List<Attachment> attachments) {
    internalNotificationService.send(
        new InternalNotificationMessage(registration, attachments)
    );
  }

  public void sendApplicantNotification(@Nonnull ExpertRegistration registration) {
    templatedEmailSender.send(
        TemplatedMessage.builder()
            .recipients(Set.of(registration.getEmail()))
            .collate(true)
            .useDefaultSubject(true)
            .template(new DefaultAnswerMessageTemplate())
            .build()
    );
  }
}
