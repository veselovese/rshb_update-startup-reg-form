package ru.rshb.intech.digital.puzzles.core.crossword.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordParticipantCounterRepository;
import ru.rshb.intech.digital.puzzles.entity.CrosswordParticipantCounter;

@Service
@RequiredArgsConstructor
public class CrosswordCounterServiceImpl implements CrosswordCounterService {

  private static final long DEFAULT_PARTICIPANT_COUNTER_VAL = 0L;

  private final CrosswordParticipantCounterRepository participantCounterRepository;
  private final CrosswordScoresService crosswordScoresService;

  @Nonnull
  @Override
  @Transactional
  public CrosswordParticipantCounter incrementParticipantCounter(@Nonnull String crosswordId, @Nonnull String sessionId) {

    final var counter = getParticipantCounter(crosswordId, sessionId);
    final var userAlreadySolvedCrossword = crosswordScoresService.isScoreExist(crosswordId, sessionId);
    if (userAlreadySolvedCrossword) {
      return counter;
    }

    counter.setInteractionCount(counter.getInteractionCount() + 1);
    return participantCounterRepository.save(counter);
  }

  @Nonnull
  @Override
  public CrosswordParticipantCounter getParticipantCounter(@Nonnull String crosswordId, @Nonnull String sessionId) {
    return participantCounterRepository.findById(crosswordId)
        .orElse(new CrosswordParticipantCounter(crosswordId, DEFAULT_PARTICIPANT_COUNTER_VAL));
  }
}
