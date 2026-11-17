package ru.rshb.intech.digital.feature.contests.patent.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.contests.patent.entity.PatentContestRegistration;

public interface PatentContestRegistrationService {

  @Nonnull
  PatentContestRegistration save(@Nonnull PatentContestRegistration registration);

  long getRegistrationCountByEmail(@Nonnull String email);

  @Nonnull
  String[] getLogoSupportedExtensions();

  @Nonnull
  String[] getPresentationSupportedExtensions();

}
