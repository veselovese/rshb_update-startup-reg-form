package ru.rshb.intech.digital.puzzles.core.crossword.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.puzzles.entity.CrosswordCategory;

import java.util.Optional;

public interface CrosswordCategoryRepository extends JpaRepository<CrosswordCategory, Long> {

  @Nonnull
  Optional<CrosswordCategory> findByExternalId(@Nonnull String externalId);

  @Nonnull
  Optional<CrosswordCategory> findByTitleIgnoreCase(@Nonnull String title);
}
