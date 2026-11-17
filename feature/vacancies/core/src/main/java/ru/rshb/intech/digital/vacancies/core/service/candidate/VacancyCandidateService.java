package ru.rshb.intech.digital.vacancies.core.service.candidate;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.vacancies.entity.CandidateCv;

public interface VacancyCandidateService {

  @Nonnull
  CandidateCv save(@Nonnull CandidateCv cv);

  @Nonnull
  String[] getFormAttachmentAllowedExtensions();
}
