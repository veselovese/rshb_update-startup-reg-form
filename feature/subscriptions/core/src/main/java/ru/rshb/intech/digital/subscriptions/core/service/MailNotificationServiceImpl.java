package ru.rshb.intech.digital.subscriptions.core.service;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.NotificationService;
import ru.rshb.intech.digital.notifications.api.message.BasicMessage;
import ru.rshb.intech.digital.notifications.api.message.MessageContent;
import ru.rshb.intech.digital.notifications.mail.channel.EmailChannels;
import ru.rshb.intech.digital.subscriptions.core.exception.SubscriptionException;
import ru.rshb.intech.digital.subscriptions.core.service.activation.ActivationEmailTemplate;
import ru.rshb.intech.digital.subscriptions.core.service.activation.ActivationInfo;
import ru.rshb.intech.digital.subscriptions.core.service.activation.ActivationLinkFactory;

import java.nio.charset.StandardCharsets;
import java.util.Set;

@Slf4j
@Service
public class MailNotificationServiceImpl implements MailNotificationService {

  private static final String DEFAULT_ACTIVATION_SUBJECT = I18n.of("subscriptions.activation.email.subject");

  private final NotificationService notificationService;
  private final ActivationEmailTemplate template;
  private final ActivationLinkFactory activationLinkFactory;


  public MailNotificationServiceImpl(@Nonnull NotificationService notificationService,
                                     @Nonnull ActivationEmailTemplate template,
                                     @Nonnull ActivationLinkFactory activationLinkFactory) {
    this.notificationService = notificationService;
    this.template = template;
    this.activationLinkFactory = activationLinkFactory;
  }


  @Override
  public void sendActivationLetter(@Nonnull String email, @Nonnull String activationCode) {

    try {
      final var activationLink = activationLinkFactory.createNewLink(activationCode);
      final var content = template.toHtml(new ActivationInfo(activationLink));

      notificationService.send(
          BasicMessage.builder()
              .channels(Set.of(EmailChannels.DEFAULT))
              .recipients(Set.of(email))
              .collate(false)
              .content(
                  MessageContent.builder()
                      .subject(DEFAULT_ACTIVATION_SUBJECT)
                      .data(content.getBytes(StandardCharsets.UTF_8))
                      .build()
              )
              .build()
      );

    } catch (Exception e) {
      throw new SubscriptionException("Activation letter [" + email + "] sending error ", e);
    }

    log.info("Activation code [{}] sent to [{}]", activationCode, email);
  }
}
