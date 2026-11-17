package ru.rshb.intech.digital.puzzles.core.crossword.service.category;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.puzzles.dto.CrosswordCategoryDto;

import java.util.List;

public interface CrosswordCategoryService {

  @Nonnull
  List<CrosswordCategoryDto> getAll();
}
