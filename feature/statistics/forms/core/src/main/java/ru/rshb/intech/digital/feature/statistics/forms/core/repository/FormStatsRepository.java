package ru.rshb.intech.digital.feature.statistics.forms.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.statistics.forms.entity.FormStatsReport;

import java.util.Optional;

public interface FormStatsRepository extends JpaRepository<FormStatsReport, Long> {

  @Nonnull
  Optional<FormStatsReport> findFirstByOrderByCreatedDateDesc();
}
