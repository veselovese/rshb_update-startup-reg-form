package ru.rshb.intech.digital.vacancies.core.service;

import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.vacancies.core.exception.VacancyNotFoundException;
import ru.rshb.intech.digital.vacancies.core.repository.VacancyRepository;
import ru.rshb.intech.digital.vacancies.core.repository.specification.VacancySpecifications;
import ru.rshb.intech.digital.vacancies.entity.Vacancy;
import ru.rshb.intech.digital.vacancies.hh.dto.filter.CriteriaType;
import ru.rshb.intech.digital.vacancies.hh.dto.filter.FilterValueDto;
import ru.rshb.intech.digital.vacancies.hh.dto.filter.SpecializationFilterValueDto;
import ru.rshb.intech.digital.vacancies.hh.dto.filter.VacancyFilterDto;
import ru.rshb.intech.digital.vacancies.hh.dto.filter.VacancyFilterLookupDto;
import ru.rshb.intech.digital.vacancies.model.VacanciesFilter;
import ru.rshb.intech.digital.vacancies.model.VacancyDto;
import ru.rshb.intech.digital.vacancies.model.VacancyHighlightDto;
import ru.rshb.intech.digtial.commons.dto.Page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.FULL_DATE;
import static ru.rshb.intech.digital.vacancies.core.repository.specification.VacancySpecifications.areaLike;
import static ru.rshb.intech.digital.vacancies.core.repository.specification.VacancySpecifications.companyIn;
import static ru.rshb.intech.digital.vacancies.core.repository.specification.VacancySpecifications.employmentIn;
import static ru.rshb.intech.digital.vacancies.core.repository.specification.VacancySpecifications.experienceIn;
import static ru.rshb.intech.digital.vacancies.core.repository.specification.VacancySpecifications.specializationIn;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

  private static final String RESET_VACANCY_ID_SEQ_SQL =
      """
      alter sequence if exists digital_vacancies.o_plt_vacancies_id_seq
      restart with 1;
      """;

  private final VacancyRepository vacancyRepository;
  private final VacancyCompanyService companyService;
  private final VacancySpecializationService specializationService;
  private final VacancyEmploymentService employmentService;
  private final VacancyExperienceService experienceService;
  private final VacancyAreaService areaService;
  private final EntityManager entityManager;


  @Nonnull
  @Override
  @Transactional
  public Page<VacancyHighlightDto> findAll(@Nonnull VacanciesFilter filter, @Nonnull PageRequest pageRequest) {

    var filterSpec = filter.getSort()
        .map(VacancySpecifications::sort)
        .orElseGet(VacancySpecifications::bypass);

    if (CollectionUtils.isNotEmpty(filter.getCompanies())) {
      final var companies = companyService.findAllCompanies(filter.getCompanies());
      filterSpec = filterSpec.and(companyIn(companies));
    }

    if (filter.getCity().isPresent()) {
      final var area = areaService.getByExternalIdOrNull(filter.getCity().get());
      filterSpec = filterSpec.and(areaLike(area));
    }

    if (CollectionUtils.isNotEmpty(filter.getEmployments())) {
      final var employments = employmentService.findAll(filter.getEmployments());
      filterSpec = filterSpec.and(employmentIn(employments));
    }

    if (CollectionUtils.isNotEmpty(filter.getExperiences())) {
      final var experiences = experienceService.findAll(filter.getExperiences());
      filterSpec = filterSpec.and(experienceIn(experiences));
    }

    if (CollectionUtils.isNotEmpty(filter.getFields())) {
      final var specializations = specializationService.findAll(filter.getFields());
      filterSpec = filterSpec.and(specializationIn(specializations));
    }

    final var page = vacancyRepository.findAll(filterSpec, pageRequest);
    return Page.<VacancyHighlightDto>builder()
        .content(vacancyHighlightDtoFromEntity(page.getContent()))
        .totalElements(page.getTotalElements())
        .totalPages(page.getTotalPages())
        .numberOfElements(page.getNumberOfElements())
        .size(page.getSize())
        .page(page.getNumber())
        .build();
  }

  @Nonnull
  private List<VacancyHighlightDto> vacancyHighlightDtoFromEntity(@Nonnull Collection<Vacancy> entities) {
    return entities.stream()
        .map(entity -> vacancyHighlightDtoFromEntity(entity))
        .toList();
  }

  @Nonnull
  private VacancyHighlightDto vacancyHighlightDtoFromEntity(@Nonnull Vacancy entity) {
    final var dto = new VacancyHighlightDto();
    dto.setId(entity.getExternalId());
    dto.setTitle(entity.getTitle());
    dto.setCompany(entity.getCompany().getTitle());
    dto.setPublishDate(
        FULL_DATE.format(entity.getPublishDate())
    );
    dto.setUrl(entity.getUrl());
    dto.setApplyUrl(entity.getApplyUrl());
    dto.setCity(entity.getArea().getTitle());
    return dto;
  }

  @Nonnull
  @Override
  public Optional<VacancyDto> findByExternalId(@Nonnull String externalId) {
    return vacancyRepository.findByExternalId(externalId)
        .map(entity -> {
          final var dto = new VacancyDto();
          dto.setExternalId(entity.getExternalId());
          dto.setTitle(entity.getTitle());
          dto.setPublishDate(
              FULL_DATE.format(entity.getPublishDate())
          );
          dto.setCity(entity.getArea().getTitle());
          dto.setDescription(entity.getDescription());
          dto.setDescriptionHtml(entity.getDescriptionHtml());
          dto.setUrl(entity.getUrl());
          dto.setApplyUrl(entity.getApplyUrl());
          return dto;
        });
  }

  @Nonnull
  @Override
  @Transactional
  public VacancyDto getByExternalId(@Nonnull String externalId) {
    return findByExternalId(externalId)
        .orElseThrow(() -> new VacancyNotFoundException());
  }

  @Nonnull
  @Override
  public VacancyFilterLookupDto collectFilters() {
    final var filters = new ArrayList<VacancyFilterDto>();
    filters.add(sortFilter());
    filters.add(companyFilter());
    filters.add(citiesFilter());
    filters.add(fieldFilter());
    filters.add(employmentFilter());
    filters.add(experienceFilter());
    return new VacancyFilterLookupDto(filters);
  }

  @Override
  @Transactional
  public void cleanup() {
    synchronized (this) {
      deleteAllVacancies();
      specializationService.deleteAll();
      employmentService.deleteAll();
      experienceService.deleteAll();
      areaService.deleteAll();
    }
  }

  private void deleteAllVacancies() {
    vacancyRepository.deleteAll();
    entityManager
        .createNativeQuery(RESET_VACANCY_ID_SEQ_SQL)
        .executeUpdate();
    entityManager.flush();
  }

  @Nonnull
  @Override
  public List<Vacancy> saveAll(@Nonnull Collection<Vacancy> vacancies) {
    log.debug("Saving {} vacancies...", vacancies.size());
    return vacancyRepository.saveAll(vacancies);
  }

  /**
   * Фильтр сортировки вакансий.
   *
   * @return Варианты сортировки вакансий.
   */
  @Nonnull
  private VacancyFilterDto sortFilter() {
    return VacancyFilterDto.builder()
        .id(VacanciesFilter.SORT)
        .title(I18n.of("vacancies.filter.sort"))
        .type(CriteriaType.SINGLE)
        .values(
            List.of(
                new FilterValueDto("date", I18n.of("vacancies.filter.sort.by-date")),
                new FilterValueDto("match", I18n.of("vacancies.filter.sort.by-match"))
            )
        )
        .build();
  }

  /**
   * Фильтр вакансий по компании.
   *
   * @return Фильтр по компаниям.
   */
  @Nonnull
  private VacancyFilterDto companyFilter() {

    final var filterValues = companyService.findAllCompanies()
        .stream()
        .map(company -> new FilterValueDto(company.getExternalId(), company.getTitle()))
        .toList();

    return VacancyFilterDto.builder()
        .id(VacanciesFilter.COMPANIES)
        .title(I18n.of("vacancies.filter.company"))
        .type(CriteriaType.MULTIPLE)
        .values(filterValues)
        .build();
  }

  /**
   * Фильтр вакансий по городам.
   *
   * @return Фильтр по городам.
   */
  @Nonnull
  private VacancyFilterDto citiesFilter() {

    final var filterValues = areaService.findAll()
        .stream()
        .map(city ->
                 new FilterValueDto(
                     String.valueOf(city.getExternalId()),
                     city.getTitle()
                 )
        ).toList();

    return VacancyFilterDto.builder()
        .id(VacanciesFilter.CITY)
        .title(I18n.of("vacancies.filter.city"))
        .type(CriteriaType.SINGLE)
        .values(filterValues)
        .build();
  }

  /**
   * Фильтр вакансий по направлениям деятельности.
   *
   * @return Фильтр по направлениям.
   */
  @Nonnull
  private VacancyFilterDto fieldFilter() {

    final var filterValues = specializationService.findAll()
        .stream()
        .map(spec -> {
          final var value = new SpecializationFilterValueDto();
          value.setId(spec.getExternalId());
          value.setTitle(spec.getTitle());
          value.setProfAreaId(spec.getProfAreaId());
          value.setProfAreaTitle(spec.getProfAreaTitle());
          return value;
        })
        .toList();

    return VacancyFilterDto.builder()
        .id(VacanciesFilter.FIELDS)
        .title(I18n.of("vacancies.filter.field"))
        .type(CriteriaType.MULTIPLE)
        .values(filterValues)
        .build();
  }

  /**
   * Фильтр вакансий по типу занятости.
   *
   * @return Фильтр по типу занятости.
   */
  @Nonnull
  private VacancyFilterDto employmentFilter() {

    final var filterValues = employmentService.findAll()
        .stream()
        .map(emp -> new FilterValueDto(emp.getExternalId(), emp.getTitle()))
        .toList();

    return VacancyFilterDto.builder()
        .id(VacanciesFilter.EMPLOYMENTS)
        .title(I18n.of("vacancies.filter.employment"))
        .type(CriteriaType.MULTIPLE)
        .values(filterValues)
        .build();
  }

  /**
   * Фильтр вакансий по опыту работы.
   *
   * @return Фильтр по опыту.
   */
  @Nonnull
  private VacancyFilterDto experienceFilter() {

    final var filterValues = experienceService.findAll()
        .stream()
        .map(exp -> new FilterValueDto(exp.getExternalId(), exp.getTitle()))
        .toList();

    return VacancyFilterDto.builder()
        .id(VacanciesFilter.EXPERIENCES)
        .title(I18n.of("vacancies.filter.experience"))
        .type(CriteriaType.MULTIPLE)
        .values(filterValues)
        .build();
  }
}
