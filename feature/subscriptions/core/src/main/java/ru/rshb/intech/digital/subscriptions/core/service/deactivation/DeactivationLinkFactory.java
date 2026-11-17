package ru.rshb.intech.digital.subscriptions.core.service.deactivation;

import jakarta.annotation.Nonnull;

public interface DeactivationLinkFactory {

  @Nonnull
  String createNewLink(@Nonnull String deactivationCode);
}
