package ru.rshb.intech.digital.feature.downloads.core.service.form;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.downloads.core.service.form.notification.ExternalNotificationMessage;
import ru.rshb.intech.digital.feature.downloads.core.service.form.notification.InternalNotificationMessage;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;
import ru.rshb.intech.digital.notifications.api.TemplatedMessage;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;
import ru.rshb.intech.digital.notifications.internal.service.TemplatedEmailSender;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultFileRequestNotificator implements FileRequestNotificator {

  private final InternalNotificationService internalNotificationService;
  private final TemplatedEmailSender templatedEmailSender;

  @Override
  public void sendInternalNotification(@Nonnull FileRequest fileRequest) {
    internalNotificationService.send(
        new InternalNotificationMessage(fileRequest)
    );
  }

  @Override
  public void sendExternalNotification(@Nonnull FileRequest fileRequest) {

    final var emailTemplate = fileRequest.getFile().getExternalNotificationTemplate();
    if (emailTemplate == null) {
      log.warn("External notification template not set for file [{}]", fileRequest.getFile().getExternalId());
      return;
    }

    templatedEmailSender.send(
        TemplatedMessage.builder()
            .recipients(Set.of(fileRequest.getEmail()))
            .useDefaultSubject(true)
            .template(new ExternalNotificationMessage(fileRequest))
            .collate(true)
            .build()
    );
  }
}
