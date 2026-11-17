package ru.rshb.intech.digital.feedback.core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.feedback.core.FeedbackOptions;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;
import ru.rshb.intech.digital.feedback.core.repository.FeedbackReportRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {

  @Mock
  private FeedbackReportRepository feedbackReportRepository;

  @Mock
  private FeedbackOptions feedbackOptions;

  private FeedbackService feedbackService;

  @BeforeEach
  void setUp() {
    this.feedbackService = new FeedbackServiceImpl(feedbackReportRepository, feedbackOptions);
  }

  @Test
  void givenValid_save_success() {

    final var feedback = new FeedbackReport();

    given(
        feedbackReportRepository.save(any())
    ).willReturn(feedback);

    final FeedbackReport savedFeedback = feedbackService.save(feedback);

    assertThat(
        savedFeedback.getSubmitDate()
    ).isNotNull();
  }

  @Test
  void getReportsNumberByEmail() {

    final var TEST_DATA_EMAIL = "test@test.te";
    final var TEST_DATA_EXISTING_REPORTS_NUM = 2L;

    given(
        feedbackReportRepository.findCountBetweenDates(anyString(), any(), any())
    ).willReturn(TEST_DATA_EXISTING_REPORTS_NUM);

    final long reportsNumber = feedbackService.getReportsNumberByEmail(TEST_DATA_EMAIL);

    assertThat(reportsNumber)
        .isEqualTo(TEST_DATA_EXISTING_REPORTS_NUM);
  }
}