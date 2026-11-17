package ru.rshb.intech.digital.subscriptions.core.service.activation;

import jakarta.annotation.Nonnull;

public interface ActivationCodeFactory {

  @Nonnull
  String createNewCode();
}
