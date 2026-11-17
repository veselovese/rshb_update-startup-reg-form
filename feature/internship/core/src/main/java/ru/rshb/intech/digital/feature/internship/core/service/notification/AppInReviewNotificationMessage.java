package ru.rshb.intech.digital.feature.internship.core.service.notification;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.notifications.api.MessageTemplate;
import ru.rshb.intech.digital.notifications.api.TemplatedMessage;
import ru.rshb.intech.digital.notifications.internal.service.TemplatedEmailSender;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppInReviewNotificationMessage {

  private static final String TEMPLATE_KEY = "internship.applicant.request.received";

  private final TemplatedEmailSender emailNotificationSender;

  public void sendWith(@Nonnull Registration registration) {
    emailNotificationSender.send(
        message(registration.getEmail())
    );
  }

  @Nonnull
  private TemplatedMessage message(@Nonnull String email) {
    return TemplatedMessage.builder()
        .recipients(Set.of(email))
        .template(new MessageTemplate(TEMPLATE_KEY))
        .useDefaultSubject(true)
        .collate(true)
        .build();
  }
}
