package ru.rshb.intech.digital.feature.internship.core.repository.statistics;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.internship.core.entity.RegistrationMonthlyStats;

import java.util.List;
import java.util.Set;

public interface RegistrationMonthlyStatsRepository extends JpaRepository<RegistrationMonthlyStats, Long> {

  List<RegistrationMonthlyStats> findAllByFieldIn(@Nonnull Set<String> fields);
}
