package ru.rshb.intech.digital.puzzles.core.crossword.service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.puzzles.core.crossword.exception.CrosswordNotFoundException;
import ru.rshb.intech.digital.puzzles.core.crossword.exception.CrosswordQuestionNotFoundException;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordCategoryRepository;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordRepository;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordScoresRepository;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.QuestionRepository;
import ru.rshb.intech.digital.puzzles.entity.Crossword;
import ru.rshb.intech.digital.puzzles.entity.CrosswordCategory;
import ru.rshb.intech.digital.puzzles.entity.CrosswordGuessResult;
import ru.rshb.intech.digital.puzzles.entity.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
@RequiredArgsConstructor
public class CrosswordServiceImpl implements CrosswordService {

  private final CrosswordRepository crosswordRepository;
  private final CrosswordCategoryRepository crosswordCategoryRepository;
  private final QuestionRepository questionRepository;
  private final CrosswordScoresRepository scoresRepository;


  @Nonnull
  @Override
  public Crossword getCrosswordById(@Nonnull String id) {
    return crosswordRepository.findByExternalId(id)
        .orElseThrow(() -> new CrosswordNotFoundException(id));
  }

  @Nonnull
  @Override
  public List<Crossword> findByCategoryId(@Nullable String categoryId) {

    if (isBlank(categoryId)) {
      return Collections.emptyList();
    }

    return crosswordCategoryRepository.findByExternalId(categoryId)
        .map(category -> List.copyOf(category.getCrosswords()))
        .orElseGet(Collections::emptyList);
  }

  @Nonnull
  @Override
  public List<Crossword> findBy(@Nonnull CrosswordSearchOptions searchOptions) {

    if (isBlank(searchOptions.getCategoryId())) {
      return Collections.emptyList();
    }

    if (searchOptions.isShowSolved()) {
      return scoresRepository.findAllBySessionId(searchOptions.getSessionId())
          .stream()
          .map(crosswordScores -> crosswordScores.getCrossword())
          .filter(crossword -> {
            final var categoryIds = crossword.getCategories()
                .stream()
                .map(CrosswordCategory::getExternalId)
                .collect(Collectors.toSet());
            return categoryIds.contains(searchOptions.getCategoryId());
          })
          .toList();
    }

    if (searchOptions.isShowNotSolved()) {
      final var solvedCrosswords = scoresRepository.findAllBySessionId(searchOptions.getSessionId())
          .stream()
          .map(crosswordScores -> crosswordScores.getCrossword())
          .toList();

      final var allCrosswords = new ArrayList<>(
          crosswordCategoryRepository.findByExternalId(searchOptions.getCategoryId())
              .map(CrosswordCategory::getCrosswords)
              .orElseGet(Collections::emptySet)
      );

      final var notSolved = CollectionUtils.removeAll(allCrosswords, solvedCrosswords);
      return new ArrayList<>(notSolved);
    }

    final var crosswords = crosswordCategoryRepository.findByExternalId(searchOptions.getCategoryId())
        .map(CrosswordCategory::getCrosswords)
        .orElseGet(Collections::emptySet);
    return new ArrayList<>(crosswords);
  }

  @Nonnull
  @Override
  public String getAnswer(@Nonnull String questionExternalId) {

    final var question = questionRepository.findByExternalId(questionExternalId)
        .orElseThrow(() -> new CrosswordQuestionNotFoundException(questionExternalId));

    return question.getAnswer();
  }

  @Override
  public boolean isGuessRight(@Nonnull String questionId, @Nonnull String guess) {
    final var answer = getAnswer(questionId);
    return StringUtils.equalsIgnoreCase(answer, guess);
  }

  @Nonnull
  @Override
  public CrosswordGuessResult getWrongAnswers(@Nonnull String crosswordId,
                                              @Nonnull Map<String, String> providedAnswers) {

    final var crossword = getCrosswordById(crosswordId);

    final var crosswordQuestionIds = crossword.getQuestions()
        .stream()
        .map(Question::getExternalId)
        .collect(Collectors.toSet());

    final var wrongAnsweredQuestionIds = new ArrayList<String>();

    for (final var providedAnswer : providedAnswers.entrySet()) {

      final var providedQID = providedAnswer.getKey();
      final var providedGuess = providedAnswer.getValue();

      if (!crosswordQuestionIds.contains(providedQID)) {
        throw new CrosswordQuestionNotFoundException(providedQID);
      }

      final var isRight = isGuessRight(providedQID, providedGuess);
      if (!isRight) {
        wrongAnsweredQuestionIds.add(providedQID);
      }
    }

    final var result = new CrosswordGuessResult();
    result.setComplete(providedAnswers.size() == crosswordQuestionIds.size());
    result.setCrosswordId(crosswordId);
    result.setWrong(wrongAnsweredQuestionIds);
    return result;
  }

  @Nonnull
  @Override
  public List<CrosswordCategory> getAllCategories() {
    return crosswordCategoryRepository.findAll();
  }

  @Nonnull
  @Override
  public List<Crossword> findAll() {
    return crosswordRepository.findAll();
  }
}
