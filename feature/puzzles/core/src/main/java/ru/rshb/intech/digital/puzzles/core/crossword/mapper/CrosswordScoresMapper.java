package ru.rshb.intech.digital.puzzles.core.crossword.mapper;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.puzzles.dto.CrosswordScoresDto;
import ru.rshb.intech.digital.puzzles.dto.WinnerScoresDto;
import ru.rshb.intech.digital.puzzles.entity.CrosswordScores;
import ru.rshb.intech.digtial.commons.dto.Page;

import java.util.Collection;
import java.util.List;

public interface CrosswordScoresMapper {

  @Nonnull
  CrosswordScores toEntityFrom(@Nonnull String crosswordId,
                               @Nonnull WinnerScoresDto dto,
                               @Nonnull String sessionId);

  @Nonnull
  CrosswordScoresDto toDtoFrom(@Nonnull CrosswordScores entity);

  @Nonnull
  default List<CrosswordScoresDto> toDtoFrom(@Nonnull Collection<CrosswordScores> entities) {
    return entities.stream()
        .map(this::toDtoFrom)
        .toList();
  }

  @Nonnull
  default Page<CrosswordScoresDto> toDtoPageFrom(
      @Nonnull org.springframework.data.domain.Page<CrosswordScores> entitiesPage
  ) {
    final var dtos = toDtoFrom(entitiesPage.getContent());
    return Page.<CrosswordScoresDto>builder()
        .content(dtos)
        .totalElements(entitiesPage.getTotalElements())
        .numberOfElements(dtos.size())
        .page(entitiesPage.getNumber())
        .totalPages(entitiesPage.getTotalPages())
        .size(entitiesPage.getSize())
        .build();
  }
}
