package ru.rshb.intech.digital.service.content.vacancy.job.db;

import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import ru.rshb.intech.digital.service.content.vacancy.job.VacancyJobContext;
import ru.rshb.intech.digital.vacancies.core.service.VacancyAreaService;
import ru.rshb.intech.digital.vacancies.core.service.VacancyEmploymentService;
import ru.rshb.intech.digital.vacancies.core.service.VacancyExperienceService;
import ru.rshb.intech.digital.vacancies.core.service.VacancySpecializationService;
import ru.rshb.intech.digital.vacancies.entity.Area;
import ru.rshb.intech.digital.vacancies.entity.Employment;
import ru.rshb.intech.digital.vacancies.entity.Experience;
import ru.rshb.intech.digital.vacancies.entity.Specialization;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.AreaDto;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.EmploymentDto;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.ExperienceDto;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.SpecializationDto;

import java.util.Collection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class VacancyStoreReferencesTasklet implements Tasklet {

  private static final String RESET_VAC_TO_SPEC_ID_SEQ_SQL =
      """
      ALTER SEQUENCE IF EXISTS digital_vacancies.o_plt_vacancy_to_specialization_id_seq
      RESTART WITH 1;
      """;

  private final VacancyJobContext context;
  private final VacancySpecializationService specializationService;
  private final VacancyEmploymentService employmentService;
  private final VacancyExperienceService experienceService;
  private final VacancyAreaService areaService;
  private final EntityManager entityManager;

  @Override
  public RepeatStatus execute(@Nonnull StepContribution contribution,
                              @Nonnull ChunkContext chunkContext) throws Exception { // TODO refactor: divide into processing and writing

    log.debug("References storing initiated...");

    final var lookupCache = context.getLookupCache();

    resetSequences();

    final var savedSpecializations = saveSpecializationsLookup();

    context.getReferenceEntityCache()
        .specializations(savedSpecializations)
        .employments(
            employmentService.saveAll(toEmploymentEntities(lookupCache.employments()))
        )
        .experiences(
            experienceService.saveAll(toExperienceEntities(lookupCache.experiences()))
        )
        .areas(
            areaService.saveAll(toAreaEntities(lookupCache.areas()))
        );

    log.debug("References stored");

    return RepeatStatus.FINISHED;
  }

  private void resetSequences() {
    entityManager
        .createNativeQuery(RESET_VAC_TO_SPEC_ID_SEQ_SQL)
        .executeUpdate();
    entityManager.flush();
  }

  @Nonnull
  private List<Specialization> saveSpecializationsLookup() {
    final var lookupCache = context.getLookupCache();
    final var specializations = fromDto(lookupCache.specializations());
    return specializationService.saveAll(specializations);
  }

  @Nonnull
  private List<Specialization> fromDto(@Nonnull Collection<SpecializationDto> dtos) {
    return dtos.stream()
        .map(dto -> {
          final var entity = new Specialization();
          entity.setExternalId(dto.getId());
          entity.setTitle(dto.getName());
          entity.setDeleted(false);
          entity.setProfAreaId(dto.getProfAreaId());
          entity.setProfAreaTitle(dto.getProfAreaName());
          return entity;
        })
        .toList();
  }

  @Nonnull
  private List<Employment> toEmploymentEntities(@Nonnull Collection<EmploymentDto> dtos) {
    return dtos.stream()
        .map(dto -> {
          final var entity = new Employment();
          entity.setExternalId(dto.getId());
          entity.setTitle(dto.getName());
          entity.setDeleted(false);
          return entity;
        })
        .toList();
  }

  @Nonnull
  private List<Experience> toExperienceEntities(@Nonnull Collection<ExperienceDto> dtos) {
    return dtos.stream()
        .map(dto -> {
          final var entity = new Experience();
          entity.setExternalId(dto.getId());
          entity.setTitle(dto.getName());
          entity.setDeleted(false);
          return entity;
        })
        .toList();
  }

  @Nonnull
  private List<Area> toAreaEntities(@Nonnull Collection<AreaDto> dtos) {
    return dtos.stream()
        .map(dto -> {
          final var entity = new Area();
          entity.setExternalId(dto.getId());
          entity.setTitle(dto.getName());
          entity.setDeleted(false);
          return entity;
        })
        .toList();
  }

}
