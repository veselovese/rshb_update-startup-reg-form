package ru.rshb.intech.digital.puzzles.core.crossword.mapper;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.puzzles.dto.CrosswordDto;
import ru.rshb.intech.digital.puzzles.entity.Crossword;

import java.util.List;

public interface CrosswordMapper {

  @Nonnull
  CrosswordDto toDtoFromEntity(@Nonnull Crossword crossword);

  @Nonnull
  CrosswordDto toDtoFromEntity(@Nonnull Crossword crossword, boolean includeQuestions);

  @Nonnull
  List<CrosswordDto> toDtoFromEntities(@Nonnull List<Crossword> crosswords, boolean includeQuestions);
}
