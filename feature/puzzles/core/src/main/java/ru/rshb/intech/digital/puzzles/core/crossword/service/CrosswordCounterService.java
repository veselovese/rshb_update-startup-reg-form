package ru.rshb.intech.digital.puzzles.core.crossword.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.puzzles.entity.CrosswordParticipantCounter;

public interface CrosswordCounterService {

  @Nonnull
  CrosswordParticipantCounter incrementParticipantCounter(@Nonnull String crosswordId, @Nonnull String sessionId);

  @Nonnull
  CrosswordParticipantCounter getParticipantCounter(@Nonnull String corsswordId, @Nonnull String sessionId);
}
