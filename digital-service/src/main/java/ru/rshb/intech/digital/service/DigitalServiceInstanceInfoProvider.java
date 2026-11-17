package ru.rshb.intech.digital.service;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.subsystem.audit.service.InstanceInfoProvider;

@Component
public class DigitalServiceInstanceInfoProvider implements InstanceInfoProvider {

  private final String externalUrl;


  public DigitalServiceInstanceInfoProvider(@Value("${external.url}") String externalUrl) {
    this.externalUrl = externalUrl;
  }


  @Nonnull
  @Override
  public String getExternalUrl() {
    return externalUrl;
  }
}
