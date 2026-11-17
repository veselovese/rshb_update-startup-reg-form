package ru.rshb.intech.digital.puzzles.core.crossword.mapper;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.puzzles.dto.CrosswordQuestionDto;
import ru.rshb.intech.digital.puzzles.entity.Question;

import java.util.List;

public interface QuestionMapper {

  CrosswordQuestionDto toDtoFromEntity(@Nonnull Question entity);

  @Nonnull
  default List<CrosswordQuestionDto> toDtoFromEntities(@Nonnull List<Question> entities) {
    return entities.stream()
        .map(this::toDtoFromEntity)
        .toList();
  }
}
