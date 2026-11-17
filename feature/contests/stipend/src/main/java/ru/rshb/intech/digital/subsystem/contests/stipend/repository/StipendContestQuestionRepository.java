package ru.rshb.intech.digital.subsystem.contests.stipend.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendContestQuestion;

public interface StipendContestQuestionRepository extends JpaRepository<StipendContestQuestion, Long> {

  long countByPhone(@Nonnull String phone);

  long countByEmail(@Nonnull String email);

}
