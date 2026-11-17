package ru.rshb.intech.digital.service.content.vacancy.job;

import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import ru.rshb.intech.digital.service.content.vacancy.job.db.VacancyCleanupTasklet;
import ru.rshb.intech.digital.service.content.vacancy.job.db.VacancyStoreProcessor;
import ru.rshb.intech.digital.service.content.vacancy.job.db.VacancyStoreReader;
import ru.rshb.intech.digital.service.content.vacancy.job.db.VacancyStoreReferencesTasklet;
import ru.rshb.intech.digital.service.content.vacancy.job.db.VacancyStoreWriter;
import ru.rshb.intech.digital.service.content.vacancy.job.detailed.VacancyDetailedReader;
import ru.rshb.intech.digital.service.content.vacancy.job.detailed.VacancyDetailedWriter;
import ru.rshb.intech.digital.service.content.vacancy.job.list.VacancyListReader;
import ru.rshb.intech.digital.service.content.vacancy.job.list.VacancyListWriter;
import ru.rshb.intech.digital.subsystem.audit.event.AuditLogEvent;
import ru.rshb.intech.digital.subsystem.audit.event.LogLevel;
import ru.rshb.intech.digital.subsystem.events.AppEventBus;
import ru.rshb.intech.digital.subsystem.events.SubSystem;
import ru.rshb.intech.digital.vacancies.core.service.ProfRoleLookup;
import ru.rshb.intech.digital.vacancies.core.service.VacancyAreaService;
import ru.rshb.intech.digital.vacancies.core.service.VacancyCompanyService;
import ru.rshb.intech.digital.vacancies.core.service.VacancyEmploymentService;
import ru.rshb.intech.digital.vacancies.core.service.VacancyExperienceService;
import ru.rshb.intech.digital.vacancies.core.service.VacancyService;
import ru.rshb.intech.digital.vacancies.core.service.VacancySpecializationService;
import ru.rshb.intech.digital.vacancies.entity.Vacancy;
import ru.rshb.intech.digital.vacancies.hh.client.HHClientService;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.VacancyExtendedDto;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.list.VacancyItemDto;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class VacancyFetchJobFactory {

  private static final int VACANCIES_LIST_CHUNK_SIZE = 1;
  private static final int DETAILED_VACANCIES_CHUNK_SIZE = 10;
  private static final int VACANCIES_STORE_CHUNK_SIZE = 50;

  private final HHClientService hhClient;
  private final VacancyService vacancyService;
  private final VacancyCompanyService companyService;
  private final VacancySpecializationService specializationService;
  private final VacancyEmploymentService employmentService;
  private final VacancyExperienceService experienceService;
  private final VacancyAreaService areaService;
  private final JobRepository jobRepository;
  private final PlatformTransactionManager txManager;
  private final AppEventBus appEventBus;
  private final EntityManager entityManager;


  @Nonnull
  public Job createJob() {

    final var jobContext = new VacancyJobContext(profRoleLookup());

    final var accounts = companyService.findAllAccounts();
    log.info("Found accounts: {}", accounts.size());
    jobContext.addAccounts(accounts);

    final var companies = companyService.findAllCompanies();
    jobContext.getReferenceEntityCache().companies(companies);

    return new JobBuilder("vacancies-fetching", jobRepository)
        .listener(new VacancyJobListener(appEventBus))
        .incrementer(new RunIdIncrementer())
        .start(fetchVacanciesList(jobContext))
        .next(fetchDetailedVacancies(jobContext))
        .next(cleanupPreviouslyStoredVacancies())
        .next(storeReferences(jobContext))
        .next(storeVacancies(jobContext))
        .build();
  }

  @Nonnull
  private ProfRoleLookup profRoleLookup() {
    return new ProfRoleLookup(hhClient.getProfRoleCategories());
  }

  @Nonnull
  private Step fetchVacanciesList(@Nonnull VacancyJobContext jobContext) {
    return new StepBuilder("vacancies-list-fetching", jobRepository)
        .<List<VacancyItemDto>, List<VacancyItemDto>>chunk(VACANCIES_LIST_CHUNK_SIZE, txManager)
        .reader(new VacancyListReader(hhClient, jobContext))
        .writer(new VacancyListWriter(jobContext))
        .build();
  }

  @Nonnull
  private Step fetchDetailedVacancies(@Nonnull VacancyJobContext jobContext) {
    return new StepBuilder("vacancies-detailed-fetching", jobRepository)
        .<VacancyExtendedDto, VacancyExtendedDto>chunk(DETAILED_VACANCIES_CHUNK_SIZE, txManager)
        .reader(new VacancyDetailedReader(hhClient, jobContext))
        .writer(new VacancyDetailedWriter(jobContext))
        .build();
  }

  @Nonnull
  private Step cleanupPreviouslyStoredVacancies() {
    return new StepBuilder("vacancies-cleanup-db", jobRepository)
        .tasklet(new VacancyCleanupTasklet(vacancyService), txManager)
        .build();
  }

  @Nonnull
  private Step storeReferences(@Nonnull VacancyJobContext jobContext) {
    return new StepBuilder("vacancies-store-references", jobRepository)
        .tasklet(
            new VacancyStoreReferencesTasklet(
                jobContext,
                specializationService,
                employmentService,
                experienceService,
                areaService,
                entityManager
            ),
            txManager
        ).build();
  }

  @Nonnull
  private Step storeVacancies(@Nonnull VacancyJobContext jobContext) {
    return new StepBuilder("vacancies-store", jobRepository)
        .<VacancyExtendedDto, Vacancy>chunk(VACANCIES_STORE_CHUNK_SIZE, txManager)
        .reader(new VacancyStoreReader(jobContext))
        .processor(new VacancyStoreProcessor(jobContext))
        .writer(new VacancyStoreWriter(vacancyService))
        .build();
  }

  @Slf4j
  @RequiredArgsConstructor
  private static class VacancyJobListener implements JobExecutionListener {

    private final AppEventBus appEventBus;

    @Override
    public void beforeJob(@Nonnull JobExecution jobExecution) {
      log.info("Vacancies fetching job: {}", jobExecution.getStatus());
      appEventBus.log(
          new AuditLogEvent(this, LogLevel.INFO, SubSystem.VACANCIES)
              .withTitleLocalized("audit.event.vacancies.fetching.process.start.title")
              .withDescriptionLocalized("audit.event.vacancies.fetching.process.start.description")
      );
    }

    @Override
    public void afterJob(@Nonnull JobExecution jobExecution) {

      final var jobStatus = jobExecution.getStatus();
      final var jobExitStatus = jobExecution.getExitStatus();

      log.info("Vacancies fetching job: {}", jobStatus);

      if (jobStatus == BatchStatus.FAILED) {
        logFail(jobExitStatus);
      }

      logSuccess();
    }

    private void logSuccess() {
      appEventBus.log(
          new AuditLogEvent(this, LogLevel.INFO, SubSystem.VACANCIES)
              .withTitleLocalized("audit.event.vacancies.fetching.process.finish.success.title")
              .withDescriptionLocalized("audit.event.vacancies.fetching.process.finish.success.description")
      );
    }

    private void logFail(@Nonnull ExitStatus exitStatus) {
      appEventBus.log(
          new AuditLogEvent(this, LogLevel.ERROR, SubSystem.VACANCIES)
              .withTitleLocalized("audit.event.vacancies.fetching.error.title")
              .withDescription(exitStatus.getExitDescription())
      );
    }
  }
}
