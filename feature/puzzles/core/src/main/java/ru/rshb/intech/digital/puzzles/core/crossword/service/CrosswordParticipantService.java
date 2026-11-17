package ru.rshb.intech.digital.puzzles.core.crossword.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.puzzles.entity.CrosswordParticipant;

public interface CrosswordParticipantService {

  @Nonnull
  CrosswordParticipant save(@Nonnull CrosswordParticipant participant);
}
