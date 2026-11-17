package ru.rshb.intech.digital.service.content.vacancy.job.db;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import ru.rshb.intech.digital.vacancies.core.service.VacancyService;

@Slf4j
@RequiredArgsConstructor
public class VacancyCleanupTasklet implements Tasklet {

  private final VacancyService vacancyService;

  @Override
  public RepeatStatus execute(@Nonnull StepContribution contribution,
                              @Nonnull ChunkContext chunkContext) throws Exception {

    log.debug("Vacancies cleanup initiated...");
    vacancyService.cleanup();
    log.debug("Vacancies cleanup completed");
    return RepeatStatus.FINISHED;
  }
}
