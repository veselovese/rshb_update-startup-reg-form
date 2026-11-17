package ru.rshb.intech.digital.feature.contests.chef.service.contestant.notification;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.contests.chef.entity.ChefContestant;
import ru.rshb.intech.digital.notifications.api.NotificationServiceAdapter;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChefContestantNotification implements NotificationServiceAdapter<ChefContestant> {

  private final InternalNotificationService internalNotificationService;


  @Async
  @Override
  public void send(@Nonnull ChefContestant formData, @Nonnull List<Attachment> attachments) {
    sendInternalNotification(formData, attachments);
  }

  private void sendInternalNotification(@Nonnull ChefContestant formData, @Nonnull List<Attachment> attachments) {
    internalNotificationService.send(
        new InternalNotificationMessage(formData, attachments)
    );
  }
}
