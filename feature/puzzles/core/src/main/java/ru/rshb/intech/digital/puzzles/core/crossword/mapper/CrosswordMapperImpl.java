package ru.rshb.intech.digital.puzzles.core.crossword.mapper;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.puzzles.dto.CrosswordCategoryDto;
import ru.rshb.intech.digital.puzzles.dto.CrosswordDto;
import ru.rshb.intech.digital.puzzles.entity.Crossword;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CrosswordMapperImpl implements CrosswordMapper {

  private final QuestionMapper questionMapper;


  @Override
  @Nonnull
  public CrosswordDto toDtoFromEntity(@Nonnull Crossword crossword) {
    return toDtoFromEntity(crossword, true);
  }

  @Nonnull
  @Override
  public CrosswordDto toDtoFromEntity(@Nonnull Crossword crossword, boolean includeQuestions) {

    final var dto = new CrosswordDto();
    dto.setId(crossword.getExternalId());
    dto.setTitle(crossword.getTitle());
    dto.setDescription(crossword.getDescription());
    dto.setCategories(
        crossword.getCategories().stream()
            .map(entity -> new CrosswordCategoryDto(entity.getExternalId(), entity.getTitle()))
            .toList()
    );

    if (includeQuestions) {
      dto.setQuestions(
          questionMapper.toDtoFromEntities(
              List.copyOf(crossword.getQuestions())
          )
      );
    }

    return dto;
  }

  @Nonnull
  @Override
  public List<CrosswordDto> toDtoFromEntities(@Nonnull List<Crossword> crosswords, boolean includeQuestions) {
    return crosswords.stream()
        .map(crossword -> toDtoFromEntity(crossword, includeQuestions))
        .toList();
  }
}
