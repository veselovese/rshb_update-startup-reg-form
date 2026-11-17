package ru.rshb.intech.digital.notifications.api;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;

import java.util.Collections;
import java.util.List;

public interface NotificationServiceAdapter<D> {

  void send(@Nonnull D data, @Nonnull List<Attachment> attachments);

  default void send(@Nonnull D data) {
    send(data, Collections.emptyList());
  }
}
