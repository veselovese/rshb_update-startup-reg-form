package ru.rshb.intech.digital.content.provider.elasticsearch.mapper;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.content.model.Crossword;
import ru.rshb.intech.digital.content.model.Crossword.Question;
import ru.rshb.intech.digital.content.model.Crossword.Question.Direction;
import ru.rshb.intech.digital.content.provider.api.ModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.CrosswordDto;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.CrosswordQuestionDto;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.WordDirection;

import java.util.List;

@Component
public class CrosswordModelMapper implements ModelMapper<CrosswordDto, Crossword> {

  @Nonnull
  @Override
  public Crossword mapFromDto(@Nonnull CrosswordDto dto) {
    final var model = new Crossword();
    model.setId(dto.getExternalId());
    model.setTitle(dto.getTitle());
    model.setQuestions(questions(dto.getQuestions()));
    model.setCategories(dto.getCategories());
    return model;
  }

  @Nonnull
  private List<Question> questions(@Nonnull List<CrosswordQuestionDto> questionDtos) {
    return questionDtos.stream()
        .map(dto -> question(dto))
        .toList();
  }

  @Nonnull
  private Question question(@Nonnull CrosswordQuestionDto dto) {
    final var model = new Question();
    model.setId(dto.getExternalId());
    model.setTitle(dto.getTitle());
    model.setAnswer(dto.getAnswer());
    model.setDirection(direction(dto.getDirection()));
    model.setStart(dto.getStart());
    model.setCount(dto.getAnswer().length());
    return model;
  }

  @Nonnull
  private Direction direction(@Nonnull WordDirection wordDirection) {
    return switch (wordDirection) {
      case ROW -> Direction.ROW;
      case COLUMN -> Direction.COLUMN;
      default -> throw new UnsupportedOperationException("Unsupported word direction: " + wordDirection);
    };
  }
}


