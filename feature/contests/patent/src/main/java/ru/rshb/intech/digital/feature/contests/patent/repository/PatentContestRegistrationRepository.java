package ru.rshb.intech.digital.feature.contests.patent.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.contests.patent.entity.PatentContestRegistration;

public interface PatentContestRegistrationRepository extends JpaRepository<PatentContestRegistration, Long> {

  long countAllByEmailIsIgnoreCase(@Nonnull String email);

}
