package ru.rshb.intech.digital.puzzles.core.crossword.service.category;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.puzzles.core.crossword.mapper.CrosswordCategoryMapper;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordCategoryRepository;
import ru.rshb.intech.digital.puzzles.dto.CrosswordCategoryDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrosswordCategoryServiceImpl implements CrosswordCategoryService {

  private final CrosswordCategoryRepository categoryRepository;
  private final CrosswordCategoryMapper categoryMapper;

  @Nonnull
  @Override
  public List<CrosswordCategoryDto> getAll() {
    final var categories = categoryRepository.findAll();
    return categoryMapper.toDtoFromEntity(categories);
  }
}
