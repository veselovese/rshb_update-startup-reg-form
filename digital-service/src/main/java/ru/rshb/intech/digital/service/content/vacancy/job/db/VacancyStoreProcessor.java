package ru.rshb.intech.digital.service.content.vacancy.job.db;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import ru.rshb.intech.digital.service.content.vacancy.job.VacancyJobContext;
import ru.rshb.intech.digital.vacancies.entity.Specialization;
import ru.rshb.intech.digital.vacancies.entity.Vacancy;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.VacancyExtendedDto;

import java.util.Collection;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@RequiredArgsConstructor
public class VacancyStoreProcessor implements ItemProcessor<VacancyExtendedDto, Vacancy> {

  private final VacancyJobContext context;

  @Override
  public Vacancy process(@Nonnull VacancyExtendedDto item) throws Exception {

    final var refCache = context.getReferenceEntityCache();

    final var vacancy = new Vacancy();
    vacancy.setExternalId(item.getId());
    vacancy.setTitle(item.getName());
    vacancy.setDescription(item.getDescription());
    vacancy.setDescriptionHtml(item.getBrandedDescription());
    vacancy.setPublishDate(item.getPublishedAt());
    vacancy.setCompany(
        refCache.getCompanies()
            .get(item.getEmployer().getId())
    );
    vacancy.setExperience(
        refCache.getExperiences()
            .get(item.getExperience().getId())
    );
    vacancy.setEmployment(
        refCache.getEmployments()
            .get(item.getEmployment().getId())
    );
    vacancy.setArea(
        refCache.getAreas()
            .get(item.getArea().getId())
    );
    vacancy.addSpecializations(findSpecializations(item));
    vacancy.setDeleted(false);
    vacancy.setUrl(item.getAlternateUrl());
    vacancy.setApplyUrl(item.getApplyAlternateUrl());
    return vacancy;
  }

  @Nonnull
  private Collection<Specialization> findSpecializations(@Nonnull VacancyExtendedDto vacancy) {

    final var vacancyProfRoles = emptyIfNull(vacancy.getProfessionalRoles());
    final var specializationLookup = context.getReferenceEntityCache().getSpecializations();

    return vacancyProfRoles.stream()
        .map(profRole -> specializationLookup.get(profRole.getId()))
        .toList();
  }
}
