package ru.rshb.intech.digital.puzzles.core.crossword.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.puzzles.core.crossword.exception.CrosswordNotFoundException;
import ru.rshb.intech.digital.puzzles.core.crossword.exception.CrosswordParticipantValidationException;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordRepository;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordScoresRepository;
import ru.rshb.intech.digital.puzzles.entity.Crossword;
import ru.rshb.intech.digital.puzzles.entity.CrosswordScores;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrosswordScoresServiceImpl implements CrosswordScoresService {

  private final CrosswordScoresRepository crosswordScoresRepository;
  private final CrosswordRepository crosswordRepository;
  private final CrosswordScoresRepository scoresRepository;
  private final CrosswordScoresValidator scoresValidator;


  @Nonnull
  @Override
  public Page<CrosswordScores> findAllByCrosswordExternalId(@Nonnull Pageable pageable, @Nonnull String crosswordExtId) { // FIXME reduce query number

    final var crosswordId = crosswordRepository.findByExternalId(crosswordExtId)
        .map(Crossword::getId)
        .orElseThrow(() -> new CrosswordNotFoundException(crosswordExtId));

    return crosswordScoresRepository.findAllByCrossword_IdOrderByResultAsc(pageable, crosswordId);
  }

  @Nonnull
  @Override
  public List<CrosswordScores> findAllByCrosswordExternalId(@Nonnull String crosswordExtId) { // FIXME reduce query number

    final var crosswordId = crosswordRepository.findByExternalId(crosswordExtId)
        .map(Crossword::getId)
        .orElseThrow(() -> new CrosswordNotFoundException(crosswordExtId));

    return crosswordScoresRepository.findAllByCrossword_IdOrderByResultAsc(crosswordId);
  }

  @Nonnull
  @Override
  public List<CrosswordScores> findAllByCrosswordExternalIdOrderBySubmitDate(@Nonnull String cwExtId) {

    final var cwId = crosswordRepository.findByExternalId(cwExtId)
        .map(Crossword::getId)
        .orElseThrow(() -> new CrosswordNotFoundException(cwExtId));

    return crosswordScoresRepository.findAllByCrossword_IdOrderBySubmitDateAsc(cwId);
  }

  @Nonnull
  @Override
  public List<CrosswordScores> findAllBySessionId(@Nonnull String sessionId) {
    return crosswordScoresRepository.findAllBySessionId(sessionId);
  }

  @Nonnull
  @Override
  public List<CrosswordScores> findAll(@Nonnull String crosswordExtId, @Nonnull String sessionId) { // FIXME reduce query number
    final var scoresBySession = crosswordScoresRepository.findAllBySessionId(sessionId);
    return scoresBySession.stream() // FIXME single query without storing in memory
        .filter(s -> s.getCrossword().getExternalId().equals(crosswordExtId))
        .toList();
  }

  @Override
  public boolean isScoreExist(@Nonnull String crosswordExtId, @Nonnull String sessionId) {
    return !findAll(crosswordExtId, sessionId).isEmpty(); // TODO
  }

  @Nonnull
  @Override
  public CrosswordScores submitScores(@Nonnull CrosswordScores scores) {

    final var validatedScores = scoresValidator.validate(scores);
    final var sessionId = validatedScores.getScoreId().getSessionId();
    final var crosswordId = validatedScores.getScoreId().getCrosswordId();

    if (scoresRepository.existsBySessionIdAndCrossword_Id(sessionId, crosswordId)) {
      throw CrosswordParticipantValidationException.entryAlreadyExists();
    }

    validatedScores.setSubmitDate(LocalDateTime.now());

    final var savedScores = scoresRepository.save(validatedScores);
    log.debug(
        "Crossword scores saved: sessionId={}, crosswordId={}, participant={}",
        savedScores.getScoreId().getSessionId(),
        savedScores.getCrossword().getExternalId(),
        savedScores.getParticipantName()
    );
    return savedScores;
  }
}
