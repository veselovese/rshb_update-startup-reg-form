package ru.rshb.intech.digital.feature.patents.core.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;

public interface PatentPublicationService {

  @Nonnull
  PatentPublication save(@Nonnull PatentPublication publication);

  @Nonnull
  String[] getLogoAllowedExtensions();

  @Nonnull
  String[] getPresentationAllowedExtensions();
}
