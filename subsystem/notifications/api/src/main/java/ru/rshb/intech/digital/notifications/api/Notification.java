package ru.rshb.intech.digital.notifications.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageCallback;

import jakarta.annotation.Nonnull;
import java.util.Optional;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Notification {

  private final NotificationMessage message;
  private final MessageCallback beforeSend;
  private final MessageCallback afterSend;

  public Notification(@Nonnull NotificationMessage message) {
    this(message, null, null);
  }

  @Nonnull
  public static Builder builder() {
    return new Builder();
  }

  @Nonnull
  public Optional<MessageCallback> beforeSend() {
    return Optional.ofNullable(beforeSend);
  }

  @Nonnull
  public Optional<MessageCallback> afterSend() {
    return Optional.ofNullable(afterSend);
  }


  public static class Builder {

    private NotificationMessage message;
    private MessageCallback beforeSend;
    private MessageCallback afterSend;


    @Nonnull
    public Builder message(@Nonnull NotificationMessage message) {
      this.message = message;
      return this;
    }

    @Nonnull
    public Builder beforeSend(MessageCallback callback) {
      this.beforeSend = callback;
      return this;
    }

    public Builder afterSend(MessageCallback callback) {
      this.afterSend = callback;
      return this;
    }

    @Nonnull
    public Notification build() {
      return new Notification(message, beforeSend, afterSend);
    }
  }
}
