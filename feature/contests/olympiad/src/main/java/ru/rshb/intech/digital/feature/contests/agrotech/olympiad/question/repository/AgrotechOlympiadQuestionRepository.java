package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadQuestion;

public interface AgrotechOlympiadQuestionRepository extends JpaRepository<AgrotechOlympiadQuestion, Long> {

  long countAllByEmailIsIgnoreCase(@Nonnull String email);
}
