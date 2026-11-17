package ru.rshb.intech.digital.feature.innovations.core.service.general.form.statistics;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.innovations.core.entity.GeneralRequest;
import ru.rshb.intech.digital.feature.innovations.core.repository.general.GeneralRequestRepository;
import ru.rshb.intech.digital.feature.statistics.forms.api.FormUsageStatsProvider;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GeneralRequestStatsProvider implements FormUsageStatsProvider {

  private final GeneralRequestRepository requestRepository;

  @Override
  public long findRecordCountInPeriod(@Nonnull LocalDateTime periodStart, @Nonnull LocalDateTime periodEnd) {
    return requestRepository.findCountInPeriod(periodStart, periodEnd);
  }

  @Override
  public long findRecordCount() {
    return requestRepository.count();
  }

  @Nonnull
  @Override
  public Optional<LocalDateTime> findOldestRecordDate() {
    return requestRepository.findFirstByOrderBySubmitDateAsc()
        .map(GeneralRequest::getSubmitDate);
  }
}
