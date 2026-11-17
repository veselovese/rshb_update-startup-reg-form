package ru.rshb.intech.digital.feedback.core.service.report.form.statistics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;
import ru.rshb.intech.digital.feedback.core.repository.FeedbackReportRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FeedbackStatsProviderTest {

  @Mock
  private FeedbackReportRepository feedbackReportRepository;

  @InjectMocks
  private FeedbackStatsProvider feedbackStatsProvider;

  @Test
  void findRecordCountInPeriod() {

    final var ER_EXISTING_REC_NUM = 3L;
    final var TEST_DATA_START_DATE = LocalDateTime.now().minusYears(1);
    final var TEST_DATA_END_DATE = LocalDateTime.now();

    given(
        feedbackReportRepository.findCountInPeriod(any(), any())
    ).willReturn(ER_EXISTING_REC_NUM);

    final long actualRecNum = feedbackStatsProvider.findRecordCountInPeriod(TEST_DATA_START_DATE, TEST_DATA_END_DATE);

    assertThat(actualRecNum)
        .isEqualTo(ER_EXISTING_REC_NUM);
  }

  @Test
  void findRecordCount() {

    final var ER_EXISTING_REC_NUM = 2L;

    given(
        feedbackReportRepository.count()
    ).willReturn(ER_EXISTING_REC_NUM);

    final var actualRecNum = feedbackStatsProvider.findRecordCount();

    assertThat(actualRecNum)
        .isEqualTo(ER_EXISTING_REC_NUM);
  }

  @Test
  void findOldestRecordDate() {

    final var erOldestDate = LocalDateTime.now().minusYears(1);
    final var oldestRec = new FeedbackReport();
    oldestRec.setSubmitDate(erOldestDate);

    given(
        feedbackReportRepository.findFirstByOrderBySubmitDateAsc()
    ).willReturn(
        Optional.of(oldestRec)
    );

    final LocalDateTime actualOldestDate = feedbackStatsProvider.findOldestRecordDate().get();

    assertThat(actualOldestDate)
        .isEqualTo(erOldestDate);
  }
}