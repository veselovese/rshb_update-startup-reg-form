package ru.rshb.intech.digital.service.scheduler;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ErrorHandler;

@Slf4j
@AllArgsConstructor
public class RunnableTask implements Runnable {

  private Runnable wrappedRunnable;
  private ErrorHandler errorHandler;


  public RunnableTask(@Nonnull Runnable runnable) {
    this(runnable, (e) -> log.error("Task execution error", e));
  }


  @Override
  public void run() {

    if (Thread.interrupted()) {
      return;
    }

    try {
      wrappedRunnable.run();
    } catch (Exception e) {
      handleError(e);
    }
  }

  private void handleError(@Nonnull Throwable t) {
    try {
      errorHandler.handleError(t);
    } catch (Exception e) {
      log.error("Task error handling error", e);
    }
  }
}
