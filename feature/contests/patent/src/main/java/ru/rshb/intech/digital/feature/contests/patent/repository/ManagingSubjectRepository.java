package ru.rshb.intech.digital.feature.contests.patent.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.contests.patent.entity.ManagingSubjectOption;

import java.util.List;
import java.util.Optional;

public interface ManagingSubjectRepository extends JpaRepository<ManagingSubjectOption, Long> {

  @Nonnull
  Optional<ManagingSubjectOption> findByExternalIdIgnoreCase(@Nonnull String externalId);

  @Nonnull
  List<ManagingSubjectOption> findAllByDisabledIsFalse();
}
