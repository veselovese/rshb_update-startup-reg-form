package ru.rshb.intech.digital.subscriptions.core.service.activation;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UuidActivationCodeFactory implements ActivationCodeFactory {

  @Nonnull
  @Override
  public String createNewCode() {
    return UUID.randomUUID().toString();
  }
}
