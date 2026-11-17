package ru.rshb.intech.digital.content.provider.api;

import jakarta.annotation.Nonnull;
import java.util.List;

public interface ModelMapper<D, M> {

  @Nonnull
  M mapFromDto(@Nonnull D dto);

  @Nonnull
  default List<M> mapFromDto(@Nonnull List<D> dtos) {
    return dtos.stream()
        .map(this::mapFromDto)
        .toList();
  }
}
