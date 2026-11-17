package ru.rshb.intech.digital.references.universities.core.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.references.universities.entity.University;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, Long> {

  boolean existsByExternalId(@Nonnull String externalId);

  @Nonnull
  Optional<University> findByExternalId(@Nonnull String externalId);

  @Nonnull
  List<University> findByFullTitleContainingIgnoreCaseOrShortTitleContainingIgnoreCaseOrderByShortTitleAsc(String fullTitle, String shortTitle, Pageable pageable);

  @Nonnull
  default List<University> findByAnyTitle(@Nonnull String titleTemplate, @Nonnull Pageable pageable) {
    return findByFullTitleContainingIgnoreCaseOrShortTitleContainingIgnoreCaseOrderByShortTitleAsc(titleTemplate, titleTemplate, pageable);
  }

}
