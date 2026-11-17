package ru.rshb.intech.digital.feature.internship.core.service.feedback.form;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipFeedback;
import ru.rshb.intech.digital.feature.internship.core.repository.feedback.InternshipFeedbackRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class InternshipFeedbackServiceImpl implements InternshipFeedbackService {

  private static final String[] ALLOWED_PHOTO_EXTENSIONS = {"jpeg", "jpg", "png"};

  private final InternshipFeedbackRepository feedbackRepository;

  @Nonnull
  @Override
  @Transactional
  public InternshipFeedback save(@Nonnull InternshipFeedback feedback) {
    feedback.setSubmitDate(LocalDateTime.now());
    final var savedFeedback = feedbackRepository.save(feedback);
    log.info("New internship feedback has been saved");
    return savedFeedback;
  }

  @Override
  public boolean isFeedbackExist(@Nonnull String email) {
    return feedbackRepository.existsByEmailIgnoreCase(email);
  }

  @Nonnull
  @Override
  public String[] getFormAttachmentAllowedExtensions() {
    return ALLOWED_PHOTO_EXTENSIONS;
  }
}
