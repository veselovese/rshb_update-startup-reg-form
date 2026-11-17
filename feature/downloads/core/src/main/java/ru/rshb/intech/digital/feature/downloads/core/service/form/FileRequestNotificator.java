package ru.rshb.intech.digital.feature.downloads.core.service.form;

import jakarta.annotation.Nonnull;
import org.springframework.scheduling.annotation.Async;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;

public interface FileRequestNotificator {

  void sendInternalNotification(@Nonnull FileRequest fileRequest);

  void sendExternalNotification(@Nonnull FileRequest fileRequest);

  @Async
  default void sendNotification(@Nonnull FileRequest fileRequest) {
    sendInternalNotification(fileRequest);
    sendExternalNotification(fileRequest);
  }
}
