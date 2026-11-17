package ru.rshb.intech.digital.feature.contests.chef.service.contestant.form.statistics;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.contests.chef.entity.ChefContestant;
import ru.rshb.intech.digital.feature.contests.chef.repository.ChefContestantRepository;
import ru.rshb.intech.digital.feature.statistics.forms.api.FormUsageStatsProvider;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChefStatsService implements FormUsageStatsProvider {

  private final ChefContestantRepository contestantRepository;

  @Override
  public long findRecordCountInPeriod(@Nonnull LocalDateTime periodStart, @Nonnull LocalDateTime periodEnd) {
    return contestantRepository.findCountInPeriod(periodStart, periodEnd);
  }

  @Override
  public long findRecordCount() {
    return contestantRepository.count();
  }

  @Nonnull
  @Override
  public Optional<LocalDateTime> findOldestRecordDate() {
    return contestantRepository.findFirstByOrderBySubmitDateAsc()
        .map(ChefContestant::getSubmitDate);
  }
}
