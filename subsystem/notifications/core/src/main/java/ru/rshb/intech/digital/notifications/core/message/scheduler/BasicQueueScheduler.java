package ru.rshb.intech.digital.notifications.core.message.scheduler;

import ru.rshb.intech.digital.notifications.api.message.scheduler.MessageQueueScheduler;

import jakarta.annotation.Nonnull;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class BasicQueueScheduler implements MessageQueueScheduler {

  public static final int DEFAULT_PERIOD_SEC = 5;
  public static final int DEFAULT_INI_DELAY_SEC = 0;

  private final AtomicBoolean armed;
  private final ScheduledExecutorService executor;
  private final Duration period;

  public BasicQueueScheduler() {
    this(Duration.of(DEFAULT_PERIOD_SEC, ChronoUnit.SECONDS));
  }

  public BasicQueueScheduler(@Nonnull Duration period) {
    this.armed = new AtomicBoolean(false);
    this.executor = Executors.newSingleThreadScheduledExecutor();
    this.period = period;
  }

  @Override
  public Future<?> submit(@Nonnull Runnable action) {
    synchronized (this) {
      if (armed.get()) {
        return null;
      }
      armed.set(true);
      return executor.scheduleAtFixedRate(action, DEFAULT_INI_DELAY_SEC, period.getSeconds(), TimeUnit.SECONDS);
    }
  }

  @Override
  public void shutdown() {
    executor.shutdown();
  }
}
