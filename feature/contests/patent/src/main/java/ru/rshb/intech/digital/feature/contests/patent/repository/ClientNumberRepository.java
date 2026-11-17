package ru.rshb.intech.digital.feature.contests.patent.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.contests.patent.entity.ClientsNumberOption;

import java.util.List;
import java.util.Optional;

public interface ClientNumberRepository extends JpaRepository<ClientsNumberOption, Long> {

  @Nonnull
  Optional<ClientsNumberOption> findByExternalIdIgnoreCase(@Nonnull String externalId);

  @Nonnull
  List<ClientsNumberOption> findAllByDisabledIsFalse();
}
