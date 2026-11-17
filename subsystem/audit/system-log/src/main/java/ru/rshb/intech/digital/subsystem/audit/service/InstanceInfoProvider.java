package ru.rshb.intech.digital.subsystem.audit.service;

import jakarta.annotation.Nonnull;

public interface InstanceInfoProvider {

  @Nonnull
  String getExternalUrl();

}
