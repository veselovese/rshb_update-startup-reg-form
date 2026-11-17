package ru.rshb.intech.digital.subscriptions.core.service.activation;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DefaultActivationLinkFactory implements ActivationLinkFactory {

  private final String externalHost;


  public DefaultActivationLinkFactory(@Nonnull @Value("${external.url}") String externalHost) {
    this.externalHost = externalHost;
  }

  @Nonnull
  @Override
  public String createNewLink(@Nonnull String activationCode) {
    return String.format("%s/articles?code=%s", externalHost, activationCode);
  }
}
