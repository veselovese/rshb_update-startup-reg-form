package ru.rshb.intech.digital.subsystem.audit.event;

import lombok.Getter;
import ru.rshb.intech.digital.subsystem.events.EventSource;
import ru.rshb.intech.digital.subsystem.events.log.LogEvent;

import jakarta.annotation.Nonnull;

@Getter
public class AuditLogEvent extends LogEvent {

  private final LogLevel level;

  public AuditLogEvent(@Nonnull Object emitter,
                       @Nonnull LogLevel level,
                       @Nonnull EventSource source) {
    super(emitter, source);
    this.level = level;
  }

  @Nonnull
  @Override
  public AuditLogEvent withTitleLocalized(@Nonnull String titleKey) {
    return (AuditLogEvent) super.withTitleLocalized(titleKey);
  }

  @Nonnull
  @Override
  public AuditLogEvent withDescriptionLocalized(@Nonnull String descriptionKey) {
    return (AuditLogEvent) super.withDescriptionLocalized(descriptionKey);
  }
}
