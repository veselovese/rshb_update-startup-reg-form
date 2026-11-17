package ru.rshb.intech.digital.subscriptions.core.service.deactivation;

import jakarta.annotation.Nonnull;

public interface DeactivationCodeFactory {

  @Nonnull
  String createNewCode();
}
