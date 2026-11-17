package ru.rshb.intech.digital.subsystem.audit.notifications.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.notifications.api.message.Message;
import ru.rshb.intech.digital.subsystem.audit.notifications.entity.NotificationSendingStatus;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import static ru.rshb.intech.digital.subsystem.audit.notifications.entity.NotificationSendingStatus.FAIL;
import static ru.rshb.intech.digital.subsystem.audit.notifications.entity.NotificationSendingStatus.SUCCESS;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationLogEntry {

  private static final MessageDescriptionFormatter descriptionFormatter = new MessageDescriptionFormatter();

  private final NotificationSendingStatus status;
  private final String channels;
  private final String recipients;
  private final boolean collated;
  private final String title;
  private final String description;


  @Nonnull
  public static NotificationLogEntry withStatus(@Nonnull NotificationSendingStatus status,
                                                @Nonnull Message message,
                                                @Nullable Throwable throwable) {

    final var channels = String.join(",", message.getChannels());
    final var recipients = String.join(",", message.getRecipients());

    return new NotificationLogEntry(
        status,
        channels,
        recipients,
        message.isCollate(),
        message.getContent().getSubject(),
        descriptionFormatter.format(message, throwable)
    );
  }

  @Nonnull
  public static NotificationLogEntry success(@Nonnull Message message) {
    return withStatus(SUCCESS, message, null);
  }

  @Nonnull
  public static NotificationLogEntry fail(@Nonnull Message message, @Nonnull Throwable throwable) {
    return withStatus(FAIL, message, throwable);
  }
}
