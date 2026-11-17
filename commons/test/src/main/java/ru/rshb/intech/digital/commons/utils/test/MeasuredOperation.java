package ru.rshb.intech.digital.commons.utils.test;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.Callable;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MeasuredOperation {

  public static void run(@Nonnull String name,
                         @Nonnull Runnable operation) throws Exception {

    final var procedure = new Procedure(name, operation);
    procedure.run();
  }

  public static <T> T run(@Nonnull String name,
                          @Nonnull Callable<T> operation) throws Exception {

    final var function = new Function<T>(name, operation);
    return function.call();
  }

  @Slf4j
  @RequiredArgsConstructor
  private static class Procedure implements Runnable {

    private final String name;

    private final Runnable wrappedOperation;

    @Override
    public void run() {
      final var stopwatch = StopWatch.createStarted();
      wrappedOperation.run();
      log.info("{}: done. Duration: {}", name, stopwatch);
    }
  }

  @Slf4j
  @RequiredArgsConstructor
  private static class Function<T> implements Callable<T> {

    private final String name;
    private final Callable<T> wrappedOperation;

    @Override
    public T call() throws Exception {
      final var stopwatch = StopWatch.createStarted();
      final T result = wrappedOperation.call();
      log.info("{}: done. Duration: {}", name, stopwatch);
      return result;
    }
  }
}
