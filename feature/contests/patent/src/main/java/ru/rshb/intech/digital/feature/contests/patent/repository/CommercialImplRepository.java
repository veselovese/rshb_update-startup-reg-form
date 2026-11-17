package ru.rshb.intech.digital.feature.contests.patent.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.contests.patent.entity.CommercialImplOption;

import java.util.List;
import java.util.Optional;

public interface CommercialImplRepository extends JpaRepository<CommercialImplOption, Long> {

  @Nonnull
  Optional<CommercialImplOption> findByExternalIdIgnoreCase(@Nonnull String externalId);

  @Nonnull
  List<CommercialImplOption> findAllByDisabledIsFalse();
}
