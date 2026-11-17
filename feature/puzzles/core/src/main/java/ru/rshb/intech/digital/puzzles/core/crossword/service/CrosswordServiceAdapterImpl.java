package ru.rshb.intech.digital.puzzles.core.crossword.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.puzzles.core.crossword.mapper.CrosswordMapper;
import ru.rshb.intech.digital.puzzles.dto.CrosswordDto;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CrosswordServiceAdapterImpl implements CrosswordServiceAdapter {

  private final CrosswordMapper mapper;
  private final CrosswordService crosswordService;
  private final CrosswordCounterService crosswordCounterService;


  @Nonnull
  @Override
  public CrosswordDto byId(@Nonnull String crosswordId, @Nonnull String sessionId) {

    final var crossword = crosswordService.getCrosswordById(crosswordId);
    final var counter = crosswordCounterService.incrementParticipantCounter(crosswordId, sessionId);

    final var dto = mapper.toDtoFromEntity(crossword);
    dto.setParticipantsCount(counter.getInteractionCount());

    return dto;
  }

  @Nonnull
  @Override
  public List<CrosswordDto> bySearchOptions(@Nonnull String sessionId,
                                            @Nonnull CrosswordSearchOptions searchOptions) {

    final var crosswords = crosswordService.findBy(searchOptions);
    final var dtos = mapper.toDtoFromEntities(crosswords, searchOptions.isWithQuestions());

    for (final var crosswordDto : dtos) {
      final var participantCounter = crosswordCounterService.getParticipantCounter(crosswordDto.getId(), sessionId);
      crosswordDto.setParticipantsCount(participantCounter.getInteractionCount());
    }

    return dtos;
  }
}
