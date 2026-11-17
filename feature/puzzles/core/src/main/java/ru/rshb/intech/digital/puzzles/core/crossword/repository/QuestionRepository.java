package ru.rshb.intech.digital.puzzles.core.crossword.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.puzzles.entity.Question;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

  @Nonnull
  Optional<Question> findByExternalId(@Nonnull String externalId);
}
