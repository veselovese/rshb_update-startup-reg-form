package ru.rshb.intech.digital.puzzles.core.crossword.mapper;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.puzzles.core.crossword.exception.CrosswordNotFoundException;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordRepository;
import ru.rshb.intech.digital.puzzles.dto.CrosswordScoresDto;
import ru.rshb.intech.digital.puzzles.dto.WinnerScoresDto;
import ru.rshb.intech.digital.puzzles.entity.CrosswordScores;
import ru.rshb.intech.digital.puzzles.entity.ScoreId;

@Component
@RequiredArgsConstructor
public class CrosswordScoresMapperImpl implements CrosswordScoresMapper {

  private final CrosswordRepository crosswordRepository;

  @Nonnull
  @Override
  public CrosswordScores toEntityFrom(@Nonnull String crosswordId,
                                      @Nonnull WinnerScoresDto dto,
                                      @Nonnull String sessionId) {

    final var crossword = crosswordRepository.findByExternalId(crosswordId)
        .orElseThrow(() -> new CrosswordNotFoundException(crosswordId));

    return CrosswordScores.builder()
        .result(dto.getResult())
        .participantName(dto.getParticipantName())
        .crossword(crossword)
        .sessionId(sessionId)
        .scoreId(new ScoreId(sessionId, crossword.getId()))
        .build();
  }

  @Nonnull
  @Override
  public CrosswordScoresDto toDtoFrom(@Nonnull CrosswordScores entity) {
    return CrosswordScoresDto.builder()
        .participantName(entity.getParticipantName())
        .result(entity.getResult())
        .submitDate(entity.getSubmitDate())
        .build();
  }
}
