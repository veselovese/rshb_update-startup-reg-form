package ru.rshb.intech.digital.subscriptions.core.service.activation;

import jakarta.annotation.Nonnull;

public interface ActivationLinkFactory {

  @Nonnull
  String createNewLink(@Nonnull String activationCode);
}
