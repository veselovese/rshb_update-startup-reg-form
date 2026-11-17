package ru.rshb.intech.digital.subsystem.audit.entity;

import jakarta.annotation.Nonnull;
import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.messages.I18n;

@Getter
@Generated
@RequiredArgsConstructor
public enum EventInitiator {

  SYSTEM("audit.initiator.system");

  private final String titleKey;

  @Nonnull
  public String getTitle() {
    return I18n.of(titleKey);
  }
}
