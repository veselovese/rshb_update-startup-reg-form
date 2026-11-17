package ru.rshb.intech.digital.feature.statistics.forms.api;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Getter;
import ru.rshb.intech.digital.feature.statistics.forms.entity.FormStatsReport;
import ru.rshb.intech.digital.feature.statistics.forms.entity.FormUsageStats;
import ru.rshb.intech.digital.messages.I18n;

import java.time.LocalDateTime;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE;


public abstract class AbstractFormUsageStatsCollector implements FormUsageStatsCollector {

  private static final long DEFAULT_USAGE_COUNT = 0L;
  private static final String STATS_NA_LABEL = I18n.of("statistics.data.not.available.short");

  @Getter
  private final String formTitle;
  private final FormUsageStatsProvider statsProvider;


  public AbstractFormUsageStatsCollector(@Nonnull String titleKey,
                                         @Nonnull FormUsageStatsProvider statsProvider) {

    this.formTitle = I18n.of(titleKey);
    this.statsProvider = statsProvider;
  }

  @Nonnull
  @Override
  public FormUsageStats collect(
      @Nullable FormStatsReport oldReport,
      @Nonnull LocalDateTime newPeriodStart,
      @Nonnull LocalDateTime newPeriodEnd
  ) {

    final var stats = new FormUsageStats();
    stats.setFormTitle(getFormTitle());
    stats.setUsagesCount(
        statsProvider
            .findRecordCountInPeriod(newPeriodStart, newPeriodEnd)
    );
    stats.setTotalUsagesCount(
        statsProvider.findRecordCount()
    );
    stats.setDeltaUsagesCountForPeriod( // Кейс для первого репорта.
        stats.getUsagesCount()
    );
    stats.setOldestRecordDate(
        statsProvider
            .findOldestRecordDate()
            .map(SIMPLE_DATE::format)
            .orElse(STATS_NA_LABEL)
    );

    if (oldReport != null) {
      final var totalUsagesCountForPreviousPeriod = oldReport.findStatsByFormTitle(getFormTitle())
          .map(FormUsageStats::getTotalUsagesCount)
          .orElse(DEFAULT_USAGE_COUNT);
      final long delta = stats.getTotalUsagesCount() - totalUsagesCountForPreviousPeriod;
      stats.setDeltaUsagesCountForPeriod(delta);
    }

    return stats;
  }
}
