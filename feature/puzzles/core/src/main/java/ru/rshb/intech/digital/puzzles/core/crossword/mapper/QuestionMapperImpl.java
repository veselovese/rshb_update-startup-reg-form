package ru.rshb.intech.digital.puzzles.core.crossword.mapper;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.puzzles.dto.CrosswordQuestionDto;
import ru.rshb.intech.digital.puzzles.entity.Question;

@Component
public class QuestionMapperImpl implements QuestionMapper {

  @Override
  public CrosswordQuestionDto toDtoFromEntity(@Nonnull Question entity) {

    final var dto = new CrosswordQuestionDto();
    dto.setId(entity.getExternalId());
    dto.setDirection(entity.getDirection());
    dto.setTitle(entity.getTitle());

    long[] startPos = { entity.getColumn(), entity.getRow() };
    dto.setStart(startPos);
    dto.setCount(entity.getAnswer().length());

    return dto;
  }
}
