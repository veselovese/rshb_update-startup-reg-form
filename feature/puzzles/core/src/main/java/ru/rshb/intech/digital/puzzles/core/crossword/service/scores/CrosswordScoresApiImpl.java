package ru.rshb.intech.digital.puzzles.core.crossword.service.scores;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.puzzles.core.crossword.mapper.CrosswordCategoryMapper;
import ru.rshb.intech.digital.puzzles.core.crossword.mapper.CrosswordScoresMapper;
import ru.rshb.intech.digital.puzzles.core.crossword.service.CrosswordScoresService;
import ru.rshb.intech.digital.puzzles.core.crossword.service.CrosswordService;
import ru.rshb.intech.digital.puzzles.dto.CrosswordFullScoresTableDto;
import ru.rshb.intech.digital.puzzles.dto.CrosswordScoresDto;
import ru.rshb.intech.digital.puzzles.dto.CrosswordScoresTableDto;
import ru.rshb.intech.digital.puzzles.dto.WinnerScoresDto;
import ru.rshb.intech.digital.puzzles.entity.CrosswordScores;
import ru.rshb.intech.digtial.commons.dto.Page;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
@Transactional
@RequiredArgsConstructor
public class CrosswordScoresApiImpl implements CrosswordScoresApi {

  private final CrosswordScoresMapper mapper;
  private final CrosswordService crosswordService;
  private final CrosswordScoresService scoresService;
  private final CrosswordCategoryMapper categoryMapper;


  @Nonnull
  @Override
  public CrosswordScores submit(@Nonnull String crosswordId,
                                @Nonnull WinnerScoresDto participantScores,
                                @Nonnull String sessionId) {

    final var scores = mapper.toEntityFrom(crosswordId, participantScores, sessionId);
    return scoresService.submitScores(scores);
  }

  @Nonnull
  @Override
  public Page<CrosswordScoresDto> getScoresTableByCrosswordId(@Nonnull String crosswordId,
                                                              @Nonnull PageRequest pageable) {

    final var crosswordScores = scoresService.findAllByCrosswordExternalId(pageable, crosswordId);
    return mapper.toDtoPageFrom(crosswordScores);
  }

  @Nonnull
  @Override
  public CrosswordScoresTableDto getScoresTableByCrosswordId(@Nonnull String crosswordId) {

    final var crossword = crosswordService.getCrosswordById(crosswordId);
    final var categories = crossword.getCategories();
    final var scores = scoresService.findAllByCrosswordExternalId(crosswordId);

    final var scoresTable = new CrosswordScoresTableDto();
    scoresTable.setCrosswordId(crosswordId);
    scoresTable.setCrosswordCategories(categoryMapper.toDtoFromEntity(categories));
    scoresTable.setCrosswordScores(mapper.toDtoFrom(scores));

    return scoresTable;
  }

  @Nonnull
  @Override
  public List<CrosswordScoresTableDto> all(@Nonnull String sessionId) {

    final var scores = scoresService.findAllBySessionId(sessionId);
    final var scoreTables = new ArrayList<CrosswordScoresTableDto>();

    for (final var score : scores) {
      final var scoreTable = new CrosswordScoresTableDto();
      final var crossword = score.getCrossword();
      scoreTable.setCrosswordId(crossword.getExternalId());
      scoreTable.setCrosswordTitle(crossword.getTitle());
      scoreTable.setCrosswordCategories(categoryMapper.toDtoFromEntity(crossword.getCategories()));
      scoreTable.setCrosswordScores(mapper.toDtoFrom(List.of(score)));
      scoreTables.add(scoreTable);
    }

    return scoreTables;
  }

  @Nonnull
  @Override
  public CrosswordFullScoresTableDto getScoresTable(@Nullable String cwCatId) {
    return isNotBlank(cwCatId)
        ? getScoresByCwCategory(cwCatId)
        : fullScoresTable();
  }

  @Nonnull
  private CrosswordFullScoresTableDto fullScoresTable() {
    final var allScores = findAllScores();
    final var fullTable = new CrosswordFullScoresTableDto();
    fullTable.setScores(allScores);
    return fullTable;
  }

  @Nonnull
  private CrosswordFullScoresTableDto getScoresByCwCategory(@Nonnull String cwCatId) {
    final var scores = findScoresByCwCategoryId(cwCatId);
    final var scoresTable = new CrosswordFullScoresTableDto();
    scoresTable.setScores(scores);
    return scoresTable;
  }

  @Nonnull
  private List<CrosswordScoresTableDto> findScoresByCwCategoryId(@Nonnull String cwCatId) {

    final var scores = new ArrayList<CrosswordScoresTableDto>();
    final var crosswords = crosswordService.findByCategoryId(cwCatId);

    for (final var cw : crosswords) { // TODO оптимизировать запрос

      final var cwScores = scoresService.findAllByCrosswordExternalIdOrderBySubmitDate(cw.getExternalId());

      final var cwScoresDto = new CrosswordScoresTableDto();
      cwScoresDto.setCrosswordId(cw.getExternalId());
      cwScoresDto.setCrosswordTitle(cw.getTitle());
      cwScoresDto.setCrosswordScores(
          mapper.toDtoFrom(cwScores)
      );
      cwScoresDto.setCrosswordCategories(
          categoryMapper.toDtoFromEntity(cw.getCategories())
      );
      scores.add(cwScoresDto);
    }

    return scores;
  }

  @Nonnull
  private List<CrosswordScoresTableDto> findAllScores() {

    final var allScores = new ArrayList<CrosswordScoresTableDto>();
    final var allCrosswords = crosswordService.findAll();

    for (final var crossword : allCrosswords) { // TODO оптимизировать запрос
      final var scores = scoresService.findAllByCrosswordExternalId(crossword.getExternalId());

      final var scoreDto = new CrosswordScoresTableDto();
      scoreDto.setCrosswordId(crossword.getExternalId());
      scoreDto.setCrosswordTitle(crossword.getTitle());
      scoreDto.setCrosswordCategories(categoryMapper.toDtoFromEntity(crossword.getCategories()));
      scoreDto.setCrosswordScores(mapper.toDtoFrom(scores));
      allScores.add(scoreDto);
    }

    return allScores;
  }
}
