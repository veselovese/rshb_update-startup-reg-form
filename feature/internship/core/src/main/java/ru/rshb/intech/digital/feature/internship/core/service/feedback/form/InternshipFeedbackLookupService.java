package ru.rshb.intech.digital.feature.internship.core.service.feedback.form;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFeedbackLookupsDto;

public interface InternshipFeedbackLookupService {

  @Nonnull
  InternshipFeedbackLookupsDto getFeedbackFormLookups();
}
