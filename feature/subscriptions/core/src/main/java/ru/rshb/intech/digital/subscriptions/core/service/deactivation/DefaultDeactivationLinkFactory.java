package ru.rshb.intech.digital.subscriptions.core.service.deactivation;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DefaultDeactivationLinkFactory implements DeactivationLinkFactory {

  private static final String UNSUBSCRIBE_LINK_TEMPLATE = "%s/unsubscribe?code=%s";

  private final String externalHost;


  public DefaultDeactivationLinkFactory(@Nonnull @Value("${external.url}") String externalHost) {
    this.externalHost = externalHost;
  }

  @Nonnull
  @Override
  public String createNewLink(@Nonnull String deactivationCode) {
    return String.format(UNSUBSCRIBE_LINK_TEMPLATE, externalHost, deactivationCode);
  }
}
