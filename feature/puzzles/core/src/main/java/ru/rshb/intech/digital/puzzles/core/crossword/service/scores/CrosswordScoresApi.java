package ru.rshb.intech.digital.puzzles.core.crossword.service.scores;


import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.PageRequest;
import ru.rshb.intech.digital.puzzles.dto.CrosswordFullScoresTableDto;
import ru.rshb.intech.digital.puzzles.dto.CrosswordScoresDto;
import ru.rshb.intech.digital.puzzles.dto.CrosswordScoresTableDto;
import ru.rshb.intech.digital.puzzles.dto.WinnerScoresDto;
import ru.rshb.intech.digital.puzzles.entity.CrosswordScores;
import ru.rshb.intech.digtial.commons.dto.Page;

import java.util.List;

public interface CrosswordScoresApi {

  @Nonnull
  CrosswordScores submit(@Nonnull String crosswordId,
                         @Nonnull WinnerScoresDto participantScores,
                         @Nonnull String sessionId);

  @Nonnull
  Page<CrosswordScoresDto> getScoresTableByCrosswordId(@Nonnull String crosswordId,
                                                       @Nonnull PageRequest pageable);

  @Nonnull
  CrosswordScoresTableDto getScoresTableByCrosswordId(@Nonnull String crosswordId);

  @Nonnull
  List<CrosswordScoresTableDto> all(@Nonnull String sessionId);

  @Nonnull
  CrosswordFullScoresTableDto getScoresTable(@Nullable String cwCatId);
}
