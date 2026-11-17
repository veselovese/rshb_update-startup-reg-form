package ru.rshb.intech.digital.feedback.core.service.report.form.statistics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.feature.statistics.forms.entity.FormStatsReport;
import ru.rshb.intech.digital.feature.statistics.forms.entity.FormUsageStats;
import ru.rshb.intech.digital.messages.I18n;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FeedbackStatsCollectorTest {

  @Mock
  private FeedbackStatsProvider feedbackStatsProvider;

  @InjectMocks
  private FeedbackStatsCollector feedbackStatsCollector;

  @Test
  void collect() {

    final var oldReport = new FormStatsReport();
    final var startDate = LocalDateTime.now().minusYears(1);
    final var endDate = LocalDateTime.now();

    final FormUsageStats formUsageStats = feedbackStatsCollector.collect(oldReport, startDate, endDate);

    assertThat(formUsageStats)
        .isNotNull();
  }

  @Test
  void getFormTitle() {
    assertThat(
        feedbackStatsCollector.getFormTitle()
    ).isEqualTo(
        I18n.of("feedback.form.title")
    );
  }
}