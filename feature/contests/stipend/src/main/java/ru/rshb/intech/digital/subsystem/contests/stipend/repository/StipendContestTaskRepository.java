package ru.rshb.intech.digital.subsystem.contests.stipend.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendTask;

import java.util.Optional;

public interface StipendContestTaskRepository extends JpaRepository<StipendTask, Long> {

  @Nonnull
  Optional<StipendTask> findByTaskIdAndEnabledTrue(@Nonnull String taskId);

  boolean existsByTaskIdIgnoreCase(@Nonnull String taskId);
}
