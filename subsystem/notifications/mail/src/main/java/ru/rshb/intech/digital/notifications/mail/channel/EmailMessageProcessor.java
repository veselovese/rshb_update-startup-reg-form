package ru.rshb.intech.digital.notifications.mail.channel;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import ru.rshb.intech.digital.notifications.api.message.Message;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageProcessor;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageProcessorContext;
import ru.rshb.intech.digital.notifications.api.sender.Sender;
import ru.rshb.intech.digital.notifications.mail.exception.MailSendingException;

import jakarta.annotation.Nonnull;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
public class EmailMessageProcessor implements MessageProcessor {

  private final JavaMailSender emailSender;

  @Override
  public void process(@Nonnull MessageProcessorContext context) {

    final var sender = context.getSender();
    final var message = context.getMessage();

    if (message.getRecipients().isEmpty()) {
      log.warn("Email sending aborted. No recipients.");
      return;
    }

    try {
      log.debug("Sending email to: {}", message.getRecipients());
      final var mimeMessage = composeMimeMessage(sender, message);
      emailSender.send(mimeMessage);
      log.debug("Email sent");
    } catch (Exception e) {
      throw new MailSendingException("Mail sending error", e);
    }
  }

  @Nonnull
  private MimeMessage composeMimeMessage(@Nonnull Sender sender, @Nonnull Message message)
      throws MessagingException, UnsupportedEncodingException {

    final MimeMessage mimeMessage = emailSender.createMimeMessage();

    final var helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
    helper.setTo(message.getRecipients().toArray(String[]::new));
    helper.setSubject(message.getContent().getSubject());

    if (sender.isUseTitle()) {
      helper.setFrom(sender.getId(), StringUtils.defaultString(sender.getTitle(), sender.getId()));
    } else {
      helper.setFrom(sender.getId());
    }

    final var text = new String(message.getContent().getData(), StandardCharsets.UTF_8).intern();
    helper.setText(text, true);

    final var attachments = message.getContent().getAttachments();
    if (!attachments.isEmpty()) {
      for (final var attachment : attachments) {

        final var filename = attachment.getOriginalFilename();
        final var inputStream = new ByteArrayResource(attachment.getData());
        final var contentType = attachment.getContentType();

        helper.addAttachment(filename, inputStream, contentType);
      }
    }

    return mimeMessage;
  }
}
