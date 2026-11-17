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
public class SubsStatsProvider implements FormUsageStatsProvider {

  private final SubscriptionRepository subscriptionRepository;

  @Override
  public long findRecordCountInPeriod(@Nonnull LocalDateTime start, @Nonnull LocalDateTime end) {
    return subscriptionRepository.findCountInPeriod(start, end);
  }

  @Override
  public long findRecordCount() {
    return subscriptionRepository.count();
  }

  @Nonnull
  @Override
  public Optional<LocalDateTime> findOldestRecordDate() {
    return subscriptionRepository.findFirstByOrderByCreationDateAsc()
        .map(Subscription::getCreationDate);
  }
}
