package ru.rshb.intech.digital.puzzles.core.crossword.service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordCategoryRepository;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordRepository;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.QuestionRepository;
import ru.rshb.intech.digital.puzzles.entity.Crossword;
import ru.rshb.intech.digital.puzzles.entity.CrosswordCategory;
import ru.rshb.intech.digital.puzzles.entity.Question;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrosswordUpdaterImpl implements CrosswordUpdater {

  private final CrosswordRepository crosswordRepository;
  private final CrosswordCategoryRepository categoryRepository;
  private final QuestionRepository crosswordQuestionService;
  private final EntityManager entityManager;


  @Override
  @Transactional
  public void update(@Nonnull List<ru.rshb.intech.digital.content.model.Crossword> cachedCrosswords) {

    if (cachedCrosswords.isEmpty()) {
      log.warn("No crosswords for sync");
      return;
    }

    // Сверяем кроссворды и удаляем из БД те, которых нет в актуальном списке
    final var cachedCwIds = cachedCrosswords.stream()
        .map(cCw -> cCw.getId().toLowerCase())
        .toList();
    crosswordRepository.deleteAllByExternalIdNotIn(cachedCwIds);
    crosswordRepository.flush();
    resetSequences();

    // Обходим список актуальных кроссвордов
    for (final var cwContent : cachedCrosswords) {

      final var cwExtId = cwContent.getId();
      log.debug("Checking crossword {}...", cwExtId);

      // Ищем кроссворд по его ID из актуального списка
      final var cwInDb = crosswordRepository.findByExternalId(cwExtId);

      // Если такого кроссворда в БД нет - конструируем и сохраняем новый
      if (cwInDb.isEmpty()) {
        final var newCw = createNewCw(cwContent);
        save(newCw);
        return;
      }

      final var existingCw = cwInDb.get();
      crosswordQuestionService.deleteAll(
          existingCw.getQuestions()
      );
      crosswordQuestionService.flush();

      // Если кроссворд существует в БД - производим процедуру обновления
      final var updatedCw = updateExistingCw(cwContent, existingCw);
      save(updatedCw);
    }
  }

  private void resetSequences() {
    entityManager.createNativeQuery(
        """
                ALTER SEQUENCE IF EXISTS digital_puzzles.o_plt_cwd_questions_id_seq
                RESTART WITH 1;
                """
    ).executeUpdate();
    entityManager.flush();
  }

  @Nonnull
  private Crossword save(@Nonnull Crossword cw) {
    log.debug("Saving crossword {}...", cw.getExternalId());
    final var savedCw = crosswordRepository.save(cw);
    log.debug("Crossword saved: {}", savedCw.getExternalId());
    return savedCw;
  }

  @Nonnull
  private Crossword createNewCw(@Nonnull ru.rshb.intech.digital.content.model.Crossword cwContent) {
    return entityFromModel(cwContent);
  }

  @Nonnull
  @Transactional
  public Crossword updateExistingCw(@Nonnull ru.rshb.intech.digital.content.model.Crossword newCwData,
                                     @Nonnull Crossword existingCw) {

    existingCw.setTitle(newCwData.getTitle());
    existingCw.setDescription(newCwData.getDescription());

    existingCw.getCategories().clear();
    existingCw.addCategories(
        categoriesByTitles(newCwData.getCategories())
    );

    existingCw.getQuestions().clear();
    existingCw.addQuestions(
        entitiesFromModels(newCwData.getQuestions())
    );

    return existingCw;
  }

  @Nonnull
  private Crossword entityFromModel(@Nonnull ru.rshb.intech.digital.content.model.Crossword model) {

    final var cwExtId = model.getId();

    log.debug("Collection categories for {}", cwExtId);
    final var categories = categoriesByTitles(model.getCategories());
    log.debug("Collected {} categories", categories.size());

    log.debug("Collecting questions for {}", cwExtId);
    final var questions = entitiesFromModels(model.getQuestions());
    log.debug("Collected {} questions", questions.size());

    return newCrossword(model, categories, questions);
  }

  @Nonnull
  private Crossword newCrossword(@Nonnull ru.rshb.intech.digital.content.model.Crossword cwContent,
                                 @Nonnull Collection<CrosswordCategory> categories,
                                 @Nonnull Collection<Question> questions) {
    final var cw = new Crossword();
    cw.setExternalId(cwContent.getId());
    cw.setTitle(cwContent.getTitle());
    cw.setDescription(cwContent.getDescription());
    cw.addCategories(categories);
    cw.addQuestions(questions);
    return cw;
  }

  @Nonnull
  private Set<CrosswordCategory> categoriesByTitles(@Nonnull Collection<String> titles) {
    return titles.stream()
        .map(cTitle -> findCategoryByTitle(cTitle))
        .filter(Objects::nonNull)
        .collect(Collectors.toSet());
  }

  @Nullable
  private CrosswordCategory findCategoryByTitle(String catTitle) {
    return isNotBlank(catTitle)
        ? categoryRepository.findByTitleIgnoreCase(catTitle)
            .orElseGet(() -> new CrosswordCategory(UUID.randomUUID().toString(), catTitle))
        : null;
  }

  @Nonnull
  private Set<Question> entitiesFromModels(
      @Nonnull Collection<ru.rshb.intech.digital.content.model.Crossword.Question> models
  ) {
    return models.stream()
        .map(this::entityFromModel)
        .collect(Collectors.toSet());
  }

  @Nonnull
  private Question entityFromModel(@Nonnull ru.rshb.intech.digital.content.model.Crossword.Question model) {
    final var entity = new Question();
    entity.setExternalId(model.getId());
    entity.setTitle(model.getTitle());
    entity.setAnswer(model.getAnswer());
    entity.setDirection(model.getDirection());
    entity.setColumn(model.getStart()[0]);
    entity.setRow(model.getStart()[1]);
    entity.setCrossword(null);
    return entity;
  }
}
