package ru.rshb.intech.digital.feature.blogs.core.experts.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;

public interface ExpertRegistrationService {

  @Nonnull
  ExpertRegistration save(@Nonnull ExpertRegistration registration);
}
