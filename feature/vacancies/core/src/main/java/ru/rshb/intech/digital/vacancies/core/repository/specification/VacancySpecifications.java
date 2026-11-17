package ru.rshb.intech.digital.vacancies.core.repository.specification;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.domain.Specification;
import ru.rshb.intech.digital.vacancies.entity.Area;
import ru.rshb.intech.digital.vacancies.entity.Company;
import ru.rshb.intech.digital.vacancies.entity.Employment;
import ru.rshb.intech.digital.vacancies.entity.Experience;
import ru.rshb.intech.digital.vacancies.entity.Specialization;
import ru.rshb.intech.digital.vacancies.entity.Vacancy;

import java.util.Collection;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

public class VacancySpecifications {

  private static final String SORT_BY_DATE = "date";
  private static final String ATTR_PUBLISH_DATE = "publishDate";
  private static final String ATTR_AREA = "area";
  private static final String ATTR_COMPANY = "company";
  private static final String ATTR_EMPLOYMENT = "employment";
  private static final String ATTR_EXPERIENCE = "experience";
  private static final String ATTR_SPECIALIZATIONS = "specializations";
  private static final String ATTR_EXTERNAL_ID = "externalId";

  @Nonnull
  public static Specification<Vacancy> bypass() {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.equal(criteriaBuilder.literal(Boolean.TRUE), Boolean.TRUE);
  }

  @Nonnull
  public static Specification<Vacancy> sort(@Nullable String sort) {

    if (SORT_BY_DATE.equalsIgnoreCase(sort)) {
      return (root, query, criteriaBuilder) -> {
        final var predicate = criteriaBuilder.equal(criteriaBuilder.literal(Boolean.TRUE), Boolean.TRUE);
        final var publishDateOrder = criteriaBuilder.desc(root.get(ATTR_PUBLISH_DATE));
        query.orderBy(publishDateOrder);
        return predicate;
      };
    }

    return bypass();
  }

  @Nonnull
  public static Specification<Vacancy> areaLike(@Nullable Area area) {
    return (root, query, criteriaBuilder) -> (area != null)
        ? criteriaBuilder.equal(root.get(ATTR_AREA), area)
        : criteriaBuilder.isNull(root.get(ATTR_AREA));
  }

  @Nonnull
  public static Specification<Vacancy> companyIn(@Nullable Collection<Company> companies) {
    return (root, query, criteriaBuilder) -> {

      final var companyPath = root.get(ATTR_COMPANY);

      if (emptyIfNull(companies).isEmpty()) {
        return criteriaBuilder.isNull(companyPath);
      }

      final var inClause = criteriaBuilder.in(companyPath);
      companies.forEach(inClause::value);
      return inClause;
    };
  }

  @Nonnull
  public static Specification<Vacancy> employmentIn(@Nonnull Collection<Employment> employments) {
    return (root, query, criteriaBuilder) -> {
      final var inClause = criteriaBuilder.in(root.get(ATTR_EMPLOYMENT));
      employments.forEach(inClause::value);
      return inClause;
    };
  }

  @Nonnull
  public static Specification<Vacancy> experienceIn(@Nonnull Collection<Experience> experiences) {
    return (root, query, criteriaBuilder) -> {
      final var inClause = criteriaBuilder.in(root.get(ATTR_EXPERIENCE));
      experiences.forEach(inClause::value);
      return inClause;
    };
  }

  @Nonnull
  public static Specification<Vacancy> specializationIn(@Nullable Collection<Specialization> specializations) {
    return (root, query, criteriaBuilder) -> {
      final var specializationJoin = root.join(ATTR_SPECIALIZATIONS);
      final var inPredicate = criteriaBuilder.in(specializationJoin.get(ATTR_EXTERNAL_ID));
      emptyIfNull(specializations)
          .stream()
          .map(Specialization::getExternalId)
          .forEach(inPredicate::value);
      query.distinct(true);
      return inPredicate;
    };
  }
}
