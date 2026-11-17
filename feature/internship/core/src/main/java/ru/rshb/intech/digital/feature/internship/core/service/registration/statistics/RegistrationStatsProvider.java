package ru.rshb.intech.digital.feature.internship.core.service.registration.statistics;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.repository.registration.RegistrationRepository;
import ru.rshb.intech.digital.feature.statistics.forms.api.FormUsageStatsProvider;

import java.time.LocalDateTime;
import java.util.Optional;

@Component("internshipRegistrationStatsProvider")
@RequiredArgsConstructor
public class RegistrationStatsProvider implements FormUsageStatsProvider {

  private final RegistrationRepository registrationRepository;

  @Override
  public long findRecordCountInPeriod(@Nonnull LocalDateTime periodStart, @Nonnull LocalDateTime periodEnd) {
    return registrationRepository.findCountInPeriod(periodStart, periodEnd);
  }

  @Override
  public long findRecordCount() {
    return registrationRepository.count();
  }

  @Nonnull
  @Override
  public Optional<LocalDateTime> findOldestRecordDate() {
    return registrationRepository.findFirstByOrderByRegistrationDateAsc()
        .map(Registration::getRegistrationDate);
  }
}
