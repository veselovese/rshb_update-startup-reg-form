package ru.rshb.intech.digital.feature.patents.core.service.publication.notification;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.notifications.api.NotificationServiceAdapter;
import ru.rshb.intech.digital.notifications.api.TemplatedMessage;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;
import ru.rshb.intech.digital.notifications.internal.service.TemplatedEmailSender;
import ru.rshb.intech.digital.references.cities.core.service.CityReferenceService;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PatentPublicationNotification implements NotificationServiceAdapter<PatentPublication> {

  private final InternalNotificationService internalNotificationService;
  private final TemplatedEmailSender templatedEmailSender;
  private final CityReferenceService cityReferenceService;


  @Async
  @Override
  public void send(@Nonnull PatentPublication data, @Nonnull List<Attachment> attachments) {
    sendInternalNotification(data, attachments);
    sendApplicantNotification(data);
  }

  private void sendInternalNotification(@Nonnull PatentPublication publication, @Nonnull List<Attachment> attachments) {
    internalNotificationService.send(
        new InternalNotificationMsg(cityReferenceService, publication, attachments)
    );
  }

  private void sendApplicantNotification(@Nonnull PatentPublication publication) {
    templatedEmailSender.send(
        TemplatedMessage.builder()
            .recipients(Set.of(publication.getEmail()))
            .useDefaultSubject(true)
            .template(new ApplicantNotificationMsg())
            .collate(true)
            .build()
    );
  }

}
