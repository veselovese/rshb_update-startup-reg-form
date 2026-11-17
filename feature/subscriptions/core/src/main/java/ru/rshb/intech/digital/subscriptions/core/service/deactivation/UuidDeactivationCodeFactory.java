package ru.rshb.intech.digital.subscriptions.core.service.deactivation;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UuidDeactivationCodeFactory implements DeactivationCodeFactory {

  @Nonnull
  @Override
  public String createNewCode() {
    return UUID.randomUUID().toString();
  }
}
