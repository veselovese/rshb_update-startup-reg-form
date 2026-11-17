package ru.rshb.intech.digital.feedback.core.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;

public interface FeedbackService {

  @Nonnull
  FeedbackReport save(@Nonnull FeedbackReport feedbackReport);

  long getReportsNumberByEmail(@Nonnull String email);

  @Nonnull
  String[] getFormAttachmentAllowedExtensions();
}
