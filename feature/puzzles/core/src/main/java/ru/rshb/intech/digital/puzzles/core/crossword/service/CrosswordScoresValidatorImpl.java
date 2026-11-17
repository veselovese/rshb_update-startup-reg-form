package ru.rshb.intech.digital.puzzles.core.crossword.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.censor.api.CensorResult;
import ru.rshb.intech.digital.censor.api.CensorService;
import ru.rshb.intech.digital.puzzles.core.crossword.exception.CrosswordNotFoundException;
import ru.rshb.intech.digital.puzzles.core.crossword.exception.CrosswordParticipantValidationException;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordRepository;
import ru.rshb.intech.digital.puzzles.entity.Crossword;
import ru.rshb.intech.digital.puzzles.entity.CrosswordScores;
import ru.rshb.intech.digital.puzzles.entity.ScoreId;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
@RequiredArgsConstructor
public class CrosswordScoresValidatorImpl implements CrosswordScoresValidator {

  private static final int MIN_SCORE_RESULT = 0;

  private final CrosswordRepository crosswordRepository;
  private final CensorService censorService;


  @Nonnull
  @Override
  public CrosswordScores validate(@Nonnull CrosswordScores crosswordScores) {
    final var validated = new CrosswordScores();

    final var scoreId = new ScoreId();
    scoreId.setCrosswordId(
        crosswordScores.getScoreId().getCrosswordId()
    );
    scoreId.setSessionId(
        validSessionId(crosswordScores.getScoreId().getSessionId())
    );
    validated.setScoreId(scoreId);

    validated.setCrossword(
        validCrossword(crosswordScores.getCrossword())
    );
    validated.setResult(
        validResult(crosswordScores.getResult())
    );
    validated.setParticipantName(
        validParticipantName(crosswordScores.getParticipantName())
    );
    return validated;
  }

  private long validResult(Long providedResult) {

    if (providedResult == null) {
      throw CrosswordParticipantValidationException.resultMissing();
    }

    if (providedResult <= MIN_SCORE_RESULT) {
      throw CrosswordParticipantValidationException.invalidResult();
    }

    return providedResult;
  }

  @Nonnull
  private String validParticipantName(String providedParticipantName) {

    if (isBlank(providedParticipantName)) {
      throw CrosswordParticipantValidationException.participantNameMissing();
    }

    if (censorService.validate(providedParticipantName) == CensorResult.VIOLATION) {
      throw CrosswordParticipantValidationException.invalidParticipantName();
    }

    return StringUtils.left(providedParticipantName.trim(), CrosswordScores.NAME_MAX_LENGTH);
  }

  @Nonnull
  private String validSessionId(String providedSessionId) {
    if (isBlank(providedSessionId)) {
      throw CrosswordParticipantValidationException.invalidSessionId();
    }
    return providedSessionId.trim();
  }

  @Nonnull
  private Crossword validCrossword(Crossword providedCrossword) {

    if (providedCrossword == null) {
      throw CrosswordParticipantValidationException.crosswordMissing();
    }

    return crosswordRepository.findByExternalId(providedCrossword.getExternalId())
        .orElseThrow(() -> new CrosswordNotFoundException(providedCrossword.getExternalId()));
  }
}
