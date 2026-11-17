package ru.rshb.intech.digital.puzzles.core.crossword.mapper;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.puzzles.dto.CrosswordCategoryDto;
import ru.rshb.intech.digital.puzzles.entity.CrosswordCategory;

import java.util.Collection;
import java.util.List;

public interface CrosswordCategoryMapper {

  @Nonnull
  CrosswordCategoryDto toDtoFromEntity(@Nonnull CrosswordCategory category);

  @Nonnull
  default List<CrosswordCategoryDto> toDtoFromEntity(@Nonnull Collection<CrosswordCategory> categories) {
    return categories.stream()
        .map(this::toDtoFromEntity)
        .toList();
  }
}
