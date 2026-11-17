package ru.rshb.intech.digital.subsystem.events.log;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.subsystem.events.AppEvent;
import ru.rshb.intech.digital.subsystem.events.EventSource;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

@Getter
public abstract class LogEvent extends AppEvent {

  private final EventSource source;

  private String title;
  private String description;
  private String stacktrace;

  public LogEvent(@Nonnull Object emitter, @Nonnull EventSource source) {
    super(emitter);

    this.source = source;
    this.title = StringUtils.EMPTY;
    this.description = StringUtils.EMPTY;
    this.stacktrace = StringUtils.EMPTY;
  }


  public String getDescription() {
    return StringUtils.isNotBlank(stacktrace)
        ? description + "\n\n" + stacktrace
        : description;
  }

  @Nonnull
  public LogEvent withTitle(@Nonnull String title) {
    this.title = title;
    return this;
  }

  @Nonnull
  public LogEvent withTitleLocalized(@Nonnull String titleKey) {
    this.title = I18n.of(titleKey);
    return this;
  }

  @Nonnull
  public LogEvent withTitleLocalized(@Nonnull String titleKey, Object... args) {
    this.title = I18n.of(titleKey, args);
    return this;
  }

  @Nonnull
  public LogEvent withDescription(@Nullable String description) {
    this.description = description;
    return this;
  }

  @Nonnull
  public LogEvent withDescriptionLocalized(@Nonnull String descriptionKey) {
    this.description = I18n.of(descriptionKey);
    return this;
  }

  @Nonnull
  public LogEvent withDescriptionLocalized(@Nonnull String descriptionKey, Object... args) {
    this.description = I18n.of(descriptionKey, args);
    return this;
  }

  @Nonnull
  public LogEvent withThrowable(@Nonnull Throwable e) {
    this.stacktrace = ExceptionUtils.getStackTrace(e);
    return this;
  }
}
