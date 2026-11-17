package ru.rshb.intech.digital.subsystem.contests.stipend.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendContestSolution;

public interface StipendContestSolutionRepository extends JpaRepository<StipendContestSolution, Long> {

  long countByTaskIdAndPhone(@Nonnull String taskId, @Nonnull String phone);

  long countByTaskIdAndEmail(@Nonnull String taskId, @Nonnull String email);

}
