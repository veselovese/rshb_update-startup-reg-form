package ru.rshb.intech.digital.subscriptions.core.service;

import jakarta.annotation.Nonnull;

public interface MailNotificationService {

  void sendActivationLetter(@Nonnull String email, @Nonnull String activationCode);
}
