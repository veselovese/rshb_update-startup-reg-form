package ru.rshb.intech.digital.feature.internship.core.service.registration;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.service.notification.AppInReviewNotificationMessage;
import ru.rshb.intech.digital.feature.internship.core.service.notification.NewAppNotificationMessage;
import ru.rshb.intech.digital.notifications.api.NotificationServiceAdapter;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InternshipRegistrationNotification implements NotificationServiceAdapter<Registration> {

  private final NewAppNotificationMessage newAppNotificationMessage;
  private final AppInReviewNotificationMessage appInReviewNotificationMessage;

  @Async
  @Override
  public void send(@Nonnull Registration registration, @Nonnull List<Attachment> attachments) {
    newAppNotificationMessage.sendWith(registration, attachments);
  }

  @Async
  public void sendApplicantNotification(@Nonnull Registration registration) {
    appInReviewNotificationMessage.sendWith(registration);
  }
}
