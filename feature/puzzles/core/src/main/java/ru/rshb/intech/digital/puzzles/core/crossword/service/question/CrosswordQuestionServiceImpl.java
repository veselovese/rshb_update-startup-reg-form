package ru.rshb.intech.digital.puzzles.core.crossword.service.question;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.puzzles.core.crossword.exception.CrosswordException;
import ru.rshb.intech.digital.puzzles.core.crossword.service.CrosswordService;
import ru.rshb.intech.digital.puzzles.dto.Answer;
import ru.rshb.intech.digital.puzzles.dto.CrosswordAnswers;
import ru.rshb.intech.digital.puzzles.dto.CrosswordCheckResult;
import ru.rshb.intech.digital.puzzles.dto.GuessResult;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CrosswordQuestionServiceImpl implements CrosswordQuestionService {

  private final CrosswordService crosswordService;

  @Nonnull
  @Override
  public Answer answer(@Nonnull String qId) {
    final var answerText = crosswordService.getAnswer(qId);
    return new Answer(answerText);
  }

  @Nonnull
  @Override
  public GuessResult isRightAnswer(@Nonnull String qId, @Nonnull String guess) {
    final var result = crosswordService.isGuessRight(qId, guess);
    return new GuessResult(result);
  }

  @Nonnull
  @Override
  public CrosswordCheckResult checkAnswers(@Nonnull String crosswordId, @Nonnull CrosswordAnswers answers) {

    final var answersList = ListUtils.emptyIfNull(answers.getQuestions()); // TODO Вынести в сервисный/валидационный слой
    if (answersList.isEmpty()) {
      throw new CrosswordException(I18n.of("puzzle.crossword.error.validation.guess.empty"));
    }

    final var guessMap = answersList.stream()
        .collect(Collectors.toMap(Answer::getQuestionId, Answer::getAnswer, (a1, a2) -> a1));

    final var guessResult = crosswordService.getWrongAnswers(crosswordId, guessMap);

    return CrosswordCheckResult.builder()
        .crosswordId(crosswordId)
        .complete(guessResult.getComplete())
        .wrong(guessResult.getWrong())
        .build();
  }
}
