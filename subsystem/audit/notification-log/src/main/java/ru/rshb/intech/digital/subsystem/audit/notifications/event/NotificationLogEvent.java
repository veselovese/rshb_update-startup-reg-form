package ru.rshb.intech.digital.subsystem.audit.notifications.event;

import lombok.Getter;
import ru.rshb.intech.digital.notifications.api.message.Message;
import ru.rshb.intech.digital.subsystem.audit.notifications.entity.NotificationSendingStatus;
import ru.rshb.intech.digital.subsystem.audit.notifications.model.MessageDescriptionFormatter;
import ru.rshb.intech.digital.subsystem.events.SubSystem;
import ru.rshb.intech.digital.subsystem.events.log.LogEvent;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import static ru.rshb.intech.digital.subsystem.audit.notifications.entity.NotificationSendingStatus.FAIL;
import static ru.rshb.intech.digital.subsystem.audit.notifications.entity.NotificationSendingStatus.SUCCESS;

@Getter
public class NotificationLogEvent extends LogEvent {

  private static final MessageDescriptionFormatter descriptionFormatter = new MessageDescriptionFormatter();

  private final String messageId;
  private final NotificationSendingStatus status;
  private final String channels;
  private final String recipients;
  private final boolean collated;


  public NotificationLogEvent(@Nonnull Object emitter,
                              @Nonnull NotificationSendingStatus status,
                              @Nonnull Message message,
                              @Nullable Throwable throwable) {

    super(emitter, SubSystem.NOTIFICATIONS);

    this.messageId = message.getId();
    this.status = status;
    this.channels = String.join(",", message.getChannels());
    this.recipients = String.join(",", message.getRecipients());
    this.collated = message.isCollate();
    this.withTitle(message.getContent().getSubject());
    this.withDescription(descriptionFormatter.format(message, throwable));
  }

  @Nonnull
  public static NotificationLogEvent success(@Nonnull Object emitter, @Nonnull Message message) {
    return new NotificationLogEvent(emitter, SUCCESS, message, null);
  }

  @Nonnull
  public static NotificationLogEvent fail(@Nonnull Object emitter,
                                          @Nonnull Message message,
                                          @Nonnull Throwable throwable) {

    return new NotificationLogEvent(emitter, FAIL, message, throwable);
  }
}
