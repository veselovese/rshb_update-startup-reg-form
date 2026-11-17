package ru.rshb.intech.digital.feature.statistics.forms.api;

import jakarta.annotation.Nonnull;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FormUsageStatsProvider {

    long findRecordCountInPeriod(@Nonnull LocalDateTime periodStart, @Nonnull LocalDateTime periodEnd);

    long findRecordCount();

    @Nonnull
    Optional<LocalDateTime> findOldestRecordDate();
}
