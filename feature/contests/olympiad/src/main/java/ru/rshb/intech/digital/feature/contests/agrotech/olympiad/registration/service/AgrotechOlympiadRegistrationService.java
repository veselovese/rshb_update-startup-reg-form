package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadRegistration;

public interface AgrotechOlympiadRegistrationService {

  @Nonnull
  AgrotechOlympiadRegistration save(@Nonnull AgrotechOlympiadRegistration registration);

  boolean isRegistrationAllowed(@Nonnull String email);
}
