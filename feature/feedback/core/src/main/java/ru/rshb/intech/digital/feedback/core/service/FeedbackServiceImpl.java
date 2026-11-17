package ru.rshb.intech.digital.feedback.core.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feedback.core.FeedbackOptions;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;
import ru.rshb.intech.digital.feedback.core.repository.FeedbackReportRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

  private static final String[] ALLOWED_EXTENSIONS = {"ppt", "pptx", "doc", "docx", "pdf"};

  private final FeedbackReportRepository reportRepository;
  private final FeedbackOptions feedbackOptions;

  @Nonnull
  @Override
  @Transactional
  public FeedbackReport save(@Nonnull FeedbackReport feedback) {
    feedback.setSubmitDate(LocalDateTime.now());
    final var savedReport = reportRepository.save(feedback);
    log.info("New feedback report has been saved");
    return savedReport;
  }

  @Override
  public long getReportsNumberByEmail(@Nonnull String email) {

    final var interval = feedbackOptions.intervalHours();
    final var periodEnd = LocalDateTime.now();
    final var periodStart = periodEnd.minusHours(interval);

    return reportRepository.findCountBetweenDates(email, periodStart, periodEnd);
  }

  @Nonnull
  @Override
  public String[] getFormAttachmentAllowedExtensions() {
    return ALLOWED_EXTENSIONS;
  }
}
