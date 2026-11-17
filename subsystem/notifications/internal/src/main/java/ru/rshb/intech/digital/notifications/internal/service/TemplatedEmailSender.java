package ru.rshb.intech.digital.notifications.internal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.notifications.api.NotificationService;
import ru.rshb.intech.digital.notifications.api.TemplatedMessage;
import ru.rshb.intech.digital.notifications.api.message.BasicMessage;
import ru.rshb.intech.digital.notifications.api.message.MessageContent;
import ru.rshb.intech.digital.notifications.core.template.service.NotificationTemplateService;
import ru.rshb.intech.digital.notifications.entity.NotificationTemplate;
import ru.rshb.intech.digital.notifications.mail.channel.EmailChannels;
import ru.rshb.intech.digital.notifications.mail.template.HtmlTemplateConverter;

import jakarta.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class TemplatedEmailSender {

  private final NotificationService notificationService;
  private final HtmlTemplateConverter templateConverter;
  private final NotificationTemplateService templateService;
  private final MessageIdGenerator msgIdGenerator;

  @Async
  public void send(@Nonnull TemplatedMessage message) {
    try {
      notificationService.send(
          BasicMessage.builder()
              .id(msgIdGenerator.nextMessageId())
              .recipients(message.getRecipients())
              .channels(Set.of(EmailChannels.MAILING))
              .content(content(message))
              .collate(message.isCollate())
              .build()
      );
    } catch (Exception e) {
      log.error("Templated email sending error", e);
    }
  }

  @Nonnull
  private MessageContent content(@Nonnull TemplatedMessage message) {

    final var template = templateService.getTemplateByKey(message.getTemplate().getTemplateKey());

    final var subject = message.isUseDefaultSubject()
        ? template.getDefaultSubject()
        : message.getTemplate().getSubject();

    final var body = messageBody(template, message);

    return MessageContent.builder()
        .subject(subject)
        .data(body)
        .attachments(message.getTemplate().getAttachments())
        .build();
  }

  @Nonnull
  private byte[] messageBody(@Nonnull NotificationTemplate template, @Nonnull TemplatedMessage message) {

    final var templateData = template.getContent();
    final var templateValues = defaultIfNull(message.getTemplate().getValues(), new Values());

    return templateConverter.toHtmlFromString(templateData, templateValues.asMap())
        .getBytes(StandardCharsets.UTF_8);
  }
}
