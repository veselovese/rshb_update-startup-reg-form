package ru.rshb.intech.digital.feedback.core.service.report.form.statistics;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.statistics.forms.api.FormUsageStatsProvider;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;
import ru.rshb.intech.digital.feedback.core.repository.FeedbackReportRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FeedbackStatsProvider implements FormUsageStatsProvider {

  private final FeedbackReportRepository reportRepository;

  @Override
  public long findRecordCountInPeriod(@Nonnull LocalDateTime periodStart, @Nonnull LocalDateTime periodEnd) {
    return reportRepository.findCountInPeriod(periodStart, periodEnd);
  }

  @Override
  public long findRecordCount() {
    return reportRepository.count();
  }

  @Nonnull
  @Override
  public Optional<LocalDateTime> findOldestRecordDate() {
    return reportRepository.findFirstByOrderBySubmitDateAsc()
        .map(FeedbackReport::getSubmitDate);
  }
}
