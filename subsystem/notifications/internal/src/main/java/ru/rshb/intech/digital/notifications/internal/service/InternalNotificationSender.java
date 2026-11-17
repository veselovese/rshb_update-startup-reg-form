package ru.rshb.intech.digital.notifications.internal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.notifications.api.Notification;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;
import ru.rshb.intech.digital.notifications.api.NotificationService;
import ru.rshb.intech.digital.notifications.api.message.BasicMessage;
import ru.rshb.intech.digital.notifications.api.message.MessageContent;
import ru.rshb.intech.digital.notifications.entity.NotificationTopic;
import ru.rshb.intech.digital.notifications.internal.repository.NotificationRecipientRepository;
import ru.rshb.intech.digital.notifications.mail.channel.EmailChannels;
import ru.rshb.intech.digital.notifications.mail.template.HtmlTemplateConverter;

import jakarta.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class InternalNotificationSender {

  private final NotificationService notificationService;
  private final NotificationRecipientRepository recipientRepository;
  private final InternalNotificationTopicService topicService;
  private final HtmlTemplateConverter templateConverter;
  private final MessageIdGenerator msgIdGenerator;

  @Async
  public void send(@Nonnull Notification notification) {
    try {

      final var message = notification.getMessage();

      final var topic = topicService.getTopicByKey(message.getTopic());

      final var recipients = recipientRepository.findEnabledEmailsByTopic(topic);
      if (recipients.isEmpty()) {
        log.warn("No recipients found for topic [{}]. Sending aborted.", topic.getKey());
        return;
      }

      final var msgId = msgIdGenerator.nextMessageId();
      final var subject = subject(message, topic);
      final var body = body(msgId, message, topic);

      final var content = MessageContent.builder()
          .subject(subject)
          .data(body)
          .attachments(message.getAttachments())
          .build();

      notificationService.send(
          BasicMessage.builder()
              .id(msgId)
              .recipients(recipients)
              .channels(Set.of(EmailChannels.DEFAULT))
              .content(content)
              .collate(topic.isCollate())
              .beforeProcess(notification.getBeforeSend())
              .afterProcess(notification.getAfterSend())
              .build()
      );
    } catch (Exception e) {
      log.error("Notification sending error", e);
    }
  }

  @Nonnull
  private String subject(@Nonnull NotificationMessage message, @Nonnull NotificationTopic topic) {
    return message.isUseDefaultSubject()
        ? topic.getTemplate().getDefaultSubject()
        : StringUtils.stripToEmpty(message.getSubject());

  }

  @Nonnull
  private byte[] body(@Nonnull String msgId,
                      @Nonnull NotificationMessage message,
                      @Nonnull NotificationTopic topic) {

    final var template = topic.getTemplate().getContent();
    final var values = ObjectUtils.defaultIfNull(message.getValues(), new Values());
    values.put("messageId", msgId);

    return templateConverter.toHtmlFromString(template, values.asMap())
        .getBytes(StandardCharsets.UTF_8);
  }
}
