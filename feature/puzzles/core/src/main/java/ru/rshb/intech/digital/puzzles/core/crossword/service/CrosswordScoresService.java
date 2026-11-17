package ru.rshb.intech.digital.puzzles.core.crossword.service;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rshb.intech.digital.puzzles.entity.CrosswordScores;

import java.util.List;

public interface CrosswordScoresService {

  @Nonnull
  Page<CrosswordScores> findAllByCrosswordExternalId(@Nonnull Pageable pageable, @Nonnull String crosswordExtId);

  @Nonnull
  List<CrosswordScores> findAllByCrosswordExternalId(@Nonnull String crosswordExtId);

  @Nonnull
  List<CrosswordScores> findAllByCrosswordExternalIdOrderBySubmitDate(@Nonnull String cwExtId);

  @Nonnull
  List<CrosswordScores> findAllBySessionId(@Nonnull String sessionId);

  @Nonnull
  List<CrosswordScores> findAll(@Nonnull String crosswordExtId, @Nonnull String sessionId);

  boolean isScoreExist(@Nonnull String crosswordExtId, @Nonnull String sessionId);

  @Nonnull
  CrosswordScores submitScores(@Nonnull CrosswordScores scores);
}
