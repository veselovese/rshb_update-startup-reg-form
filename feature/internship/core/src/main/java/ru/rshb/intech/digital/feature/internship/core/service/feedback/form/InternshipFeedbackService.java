package ru.rshb.intech.digital.feature.internship.core.service.feedback.form;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipFeedback;

public interface InternshipFeedbackService {

  @Nonnull
  InternshipFeedback save(@Nonnull InternshipFeedback feedback);

  boolean isFeedbackExist(@Nonnull String email);

  @Nonnull
  String[] getFormAttachmentAllowedExtensions();

}
