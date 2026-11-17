package ru.rshb.intech.digital.vacancies.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.vacancies.entity.Experience;

import java.util.List;
import java.util.Set;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

  @Nonnull
  List<Experience> findAllByDeletedIsFalse();

  @Nonnull
  List<Experience> findAllByExternalIdInAndDeletedIsFalse(@Nonnull Set<String> externalIds);
}
