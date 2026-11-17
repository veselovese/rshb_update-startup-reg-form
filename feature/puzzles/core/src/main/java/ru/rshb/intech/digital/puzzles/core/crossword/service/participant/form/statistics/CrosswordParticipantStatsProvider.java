package ru.rshb.intech.digital.puzzles.core.crossword.service.participant.form.statistics;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.statistics.forms.api.FormUsageStatsProvider;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordParticipantRepository;
import ru.rshb.intech.digital.puzzles.entity.CrosswordParticipant;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CrosswordParticipantStatsProvider implements FormUsageStatsProvider {

  private final CrosswordParticipantRepository participantRepository;

  @Override
  public long findRecordCountInPeriod(@Nonnull LocalDateTime periodStart, @Nonnull LocalDateTime periodEnd) {
    return participantRepository.findCountInPeriod(periodStart, periodEnd);
  }

  @Override
  public long findRecordCount() {
    return participantRepository.count();
  }

  @Nonnull
  @Override
  public Optional<LocalDateTime> findOldestRecordDate() {
    return participantRepository.findFirstByOrderBySubmitDateAsc()
        .map(CrosswordParticipant::getSubmitDate);
  }
}
