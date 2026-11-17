package ru.rshb.intech.digital.service.scheduler;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

  private final TaskScheduler taskScheduler;
  private final Map<String, ScheduledTask> futures = new ConcurrentHashMap<>();


  @Nonnull
  @Override
  public String schedule(@Nonnull String name, @Nonnull Runnable runnable, @Nonnull String cronSchedule) {

    final var wrappedRunnable = new RunnableTask(runnable);
    final var trigger = new CronTrigger(cronSchedule);

    final var future = taskScheduler.schedule(wrappedRunnable, trigger);
    if (future == null) {
      log.debug("New cron task aborted for schedule: {}", cronSchedule);
      return StringUtils.EMPTY;
    }

    final var taskId = nextTaskId();
    final var task = ScheduledTask.builder()
        .id(taskId)
        .type(TaskType.CRON)
        .name(name)
        .options(cronSchedule)
        .future(future)
        .build();
    futures.put(taskId, task);
    log.debug("New cron task [{}] {}", name, taskId);

    return taskId;
  }

  @Nonnull
  @Override
  public String schedule(@Nonnull String name, @Nonnull Runnable runnable, long intervalMs) {

    final var wrappedRunnable = new RunnableTask(runnable);
    final var future = taskScheduler.scheduleAtFixedRate(wrappedRunnable, Duration.ofMillis(intervalMs));

    final var taskId = nextTaskId();
    final var task = ScheduledTask.builder()
        .id(taskId)
        .name(name)
        .type(TaskType.FIXED_INTERVAL)
        .options(String.format("Interval %s ms", intervalMs))
        .future(future)
        .build();
    futures.put(taskId, task);

    return taskId;
  }

  @Override
  public void removeTask(@Nonnull String taskId) {
    final var task = futures.get(taskId);
    if (task == null) {
      return;
    }
    task.getFuture()
        .cancel(true);
    futures.remove(taskId);
    log.debug("Task removed: {}", taskId);
  }

  @Nonnull
  @Override
  public List<ScheduledTask> tasks() {
    return futures.values()
        .stream()
        .toList();
  }

  @Nonnull
  private String nextTaskId() {
    return UUID.randomUUID().toString();
  }

  @EventListener(ContextRefreshedEvent.class)
  public void handleContextRefreshedEvent() {
    // TODO reload and recreate futures from storage
  }
}
