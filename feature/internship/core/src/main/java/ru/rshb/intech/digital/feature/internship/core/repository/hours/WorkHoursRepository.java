package ru.rshb.intech.digital.feature.internship.core.repository.hours;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.internship.core.entity.WorkHours;

import java.util.List;

public interface WorkHoursRepository extends JpaRepository<WorkHours, Long> {

  @Nonnull
  List<WorkHours> findAllByEnabledIsTrue();

  boolean existsByTitleIgnoreCase(@Nonnull String title);

}
