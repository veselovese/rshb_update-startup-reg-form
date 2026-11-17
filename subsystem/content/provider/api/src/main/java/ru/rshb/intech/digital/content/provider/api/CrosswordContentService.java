package ru.rshb.intech.digital.content.provider.api;

import ru.rshb.intech.digital.content.model.Crossword;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public interface CrosswordContentService {

  @Nonnull
  List<Crossword> findAll();

  @Nonnull
  Optional<Crossword> findById(@Nonnull String id);

  boolean isExistsById(@Nonnull String id);
}
