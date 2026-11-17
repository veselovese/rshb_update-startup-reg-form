package ru.rshb.intech.digital.statistics.agrobit.tourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.statistics.agrobit.tourism.entity.RecreationType;

import jakarta.annotation.Nonnull;
import java.util.List;

public interface RecreationTypeRepository extends JpaRepository<RecreationType, Long> {

  @Nonnull
  List<RecreationType> findAllByExternalIdInAndEnabledIsTrueOrderByTitleAsc(@Nonnull List<String> externalIds);
}
