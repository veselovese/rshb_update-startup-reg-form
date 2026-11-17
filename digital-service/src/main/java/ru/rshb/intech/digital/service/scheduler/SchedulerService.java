package ru.rshb.intech.digital.service.scheduler;

import jakarta.annotation.Nonnull;

import java.util.List;

public interface SchedulerService {

  @Nonnull
  String schedule(@Nonnull String name, @Nonnull Runnable runnable, @Nonnull String cronSchedule);

  @Nonnull
  String schedule(@Nonnull String name, @Nonnull Runnable runnable, long intervalMs);

  void removeTask(@Nonnull String taskId);

  @Nonnull
  List<ScheduledTask> tasks();
}
