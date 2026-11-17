package ru.rshb.intech.digital.puzzles.core.crossword.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.puzzles.dto.CrosswordDto;

import java.util.List;

public interface CrosswordServiceAdapter {

  @Nonnull
  CrosswordDto byId(@Nonnull String crosswordId, @Nonnull String sessionId);

  @Nonnull
  List<CrosswordDto> bySearchOptions(@Nonnull String sessionId, @Nonnull CrosswordSearchOptions searchOptions);
}
