package ru.rshb.intech.digital.puzzles.core.crossword.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.puzzles.entity.Crossword;

import java.util.List;
import java.util.Optional;

public interface CrosswordRepository extends JpaRepository<Crossword, Long> {

  @Nonnull
  Optional<Crossword> findByExternalId(@Nonnull String externalId);

  boolean existsByExternalId(@Nonnull String externalId);

  void deleteAllByExternalIdNotIn(@Nonnull List<String> externalIds);
}
