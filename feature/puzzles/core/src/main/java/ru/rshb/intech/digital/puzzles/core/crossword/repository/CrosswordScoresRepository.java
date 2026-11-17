package ru.rshb.intech.digital.puzzles.core.crossword.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.puzzles.entity.CrosswordScores;
import ru.rshb.intech.digital.puzzles.entity.ScoreId;

import java.util.List;

public interface CrosswordScoresRepository extends JpaRepository<CrosswordScores, ScoreId> {

  @Nonnull
  Page<CrosswordScores> findAllByCrossword_IdOrderByResultAsc(@Nonnull Pageable pageable, @Nonnull Long crosswordId);

  List<CrosswordScores> findAllByCrossword_IdOrderByResultAsc(@Nonnull Long crosswordId);

  List<CrosswordScores> findAllByCrossword_IdOrderBySubmitDateAsc(@Nonnull Long cwId);

  @Nonnull
  List<CrosswordScores> findAllBySessionId(@Nonnull String sessionId);

  boolean existsBySessionIdAndCrossword_Id(@Nonnull String sessionId, @Nonnull Long crosswordId);
}
