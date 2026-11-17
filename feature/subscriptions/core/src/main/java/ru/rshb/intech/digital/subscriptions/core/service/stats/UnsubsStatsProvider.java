package ru.rshb.intech.digital.subscriptions.core.service.stats;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.statistics.forms.api.FormUsageStatsProvider;
import ru.rshb.intech.digital.subscriptions.core.repository.SubscriptionRepository;
import ru.rshb.intech.digital.subscriptions.entity.Subscription;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UnsubsStatsProvider implements FormUsageStatsProvider {

  private final SubscriptionRepository subscriptionRepository;


  @Override
  public long findRecordCountInPeriod(@Nonnull LocalDateTime periodStart, @Nonnull LocalDateTime periodEnd) {
    return subscriptionRepository.findUnsubscribedCountInPeriod(periodStart, periodEnd);
  }

  @Override
  public long findRecordCount() {
    return subscriptionRepository.findUnsubscribedCount();
  }

  @Nonnull
  @Override
  public Optional<LocalDateTime> findOldestRecordDate() {
    return subscriptionRepository.findFirstByDeactivationDateIsNotNullOrderByDeactivationDateAsc()
        .map(Subscription::getDeactivationDate);
  }
}
