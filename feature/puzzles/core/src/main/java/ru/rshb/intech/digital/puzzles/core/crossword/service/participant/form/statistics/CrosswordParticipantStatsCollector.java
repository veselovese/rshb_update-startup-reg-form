package ru.rshb.intech.digital.puzzles.core.crossword.service.participant.form.statistics;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.statistics.forms.api.AbstractFormUsageStatsCollector;

@Component
public class CrosswordParticipantStatsCollector extends AbstractFormUsageStatsCollector {

  public CrosswordParticipantStatsCollector(@Nonnull CrosswordParticipantStatsProvider statsProvider) {
    super("crossword.winner.form.title", statsProvider);
  }
}
