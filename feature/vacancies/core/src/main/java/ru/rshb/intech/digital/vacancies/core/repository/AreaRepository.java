package ru.rshb.intech.digital.vacancies.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.vacancies.entity.Area;

import java.util.List;
import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Long> {

  @Nonnull
  List<Area> findAllByDeletedIsFalse();

  @Nonnull
  Optional<Area> findByExternalIdAndDeletedIsFalse(@Nonnull String externalId);
}
