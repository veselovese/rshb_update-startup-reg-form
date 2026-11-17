package ru.rshb.intech.digital.puzzles.core.crossword.mapper;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.puzzles.dto.CrosswordCategoryDto;
import ru.rshb.intech.digital.puzzles.entity.CrosswordCategory;

@Component
@RequiredArgsConstructor
public class CrosswordCategoryMapperImpl implements CrosswordCategoryMapper {


  @Nonnull
  @Override
  public CrosswordCategoryDto toDtoFromEntity(@Nonnull CrosswordCategory category) {
    return new CrosswordCategoryDto(
        category.getExternalId(),
        category.getTitle()
    );
  }
}
