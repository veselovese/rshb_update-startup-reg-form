package ru.rshb.intech.digital.feature.startups.core.service.registration;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;

public interface StartupRegistrationService {

  @Nonnull
  StartupRegistration save(@Nonnull StartupRegistration registration);

  @Nonnull
  String[] getLogoAllowedExtensions();
}
