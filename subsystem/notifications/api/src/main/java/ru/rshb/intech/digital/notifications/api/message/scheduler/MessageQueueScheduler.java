package ru.rshb.intech.digital.notifications.api.message.scheduler;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.concurrent.Future;

public interface MessageQueueScheduler {

  @Nullable
  Future<?> submit(@Nonnull Runnable action);

  void shutdown();
}
