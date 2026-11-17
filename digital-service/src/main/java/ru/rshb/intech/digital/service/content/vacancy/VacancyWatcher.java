package ru.rshb.intech.digital.service.content.vacancy;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.service.config.options.HHConfigOptions;
import ru.rshb.intech.digital.service.content.vacancy.job.VacancyFetchJobFactory;
import ru.rshb.intech.digital.subsystem.audit.event.AuditLogEvent;
import ru.rshb.intech.digital.subsystem.audit.event.LogLevel;
import ru.rshb.intech.digital.subsystem.events.AppEventBus;
import ru.rshb.intech.digital.subsystem.events.SubSystem;

import java.util.Date;

@Slf4j
@Getter(AccessLevel.PRIVATE)
@Component
public class VacancyWatcher {

  private final JobLauncher jobLauncher;
  private final VacancyFetchJobFactory jobFactory;
  private final boolean syncEnabled;
  private final AppEventBus appEventBus;


  public VacancyWatcher(@Nonnull JobLauncher jobLauncher,
                        @Nonnull VacancyFetchJobFactory jobFactory,
                        @Nonnull HHConfigOptions hhConfigOptions,
                        @Nonnull AppEventBus appEventBus) {

    this.jobLauncher = jobLauncher;
    this.jobFactory = jobFactory;
    this.syncEnabled = BooleanUtils.toBooleanDefaultIfNull(hhConfigOptions.getSyncEnabled(), true);
    this.appEventBus = appEventBus;
  }


  @Scheduled(cron = "${vacancies.collection.cron}")
  public void fetchVacancies() {

    if (!isSyncEnabled()) {
      log.info("Vacancies sync disabled. Fetching canceled");
      appEventBus.log(
          new AuditLogEvent(this, LogLevel.INFO, SubSystem.VACANCIES)
              .withTitleLocalized("audit.event.vacancies.fetching.disabled.title")
              .withDescriptionLocalized("audit.event.vacancies.fetching.disabled.description")
      );
      return;
    }

    try {
      final var jobParameters = new JobParametersBuilder()
          .addDate("JobStartTime", new Date())
          .toJobParameters();

      final var job = getJobFactory().createJob();
      final var jobExecution = getJobLauncher()
          .run(job, jobParameters);
      log.info("Vacancy fetch job: {}", jobExecution.getStatus());

      jobExecution.getStepExecutions()
          .forEach(stepExec -> log.info("Step [{}]: {}", stepExec.getStepName(), stepExec.getStatus()));

    } catch (Exception e) {
      log.error("Fetch vacancies job launch error", e);
      appEventBus.log(
          new AuditLogEvent(this, LogLevel.ERROR, SubSystem.VACANCIES)
              .withTitleLocalized("audit.event.vacancies.fetching.error.title")
              .withDescriptionLocalized("audit.event.vacancies.fetching.error.description")
              .withThrowable(e)
      );
    }
  }
}
