package ru.rshb.intech.digital.notifications.api.message.processor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.rshb.intech.digital.notifications.api.message.Message;
import ru.rshb.intech.digital.notifications.api.sender.Sender;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
public class MessageProcessorContext {

  private final Sender sender;
  private final Message message;

  private final Map<String, Object> props = new HashMap<>();

  @Nullable
  @SuppressWarnings("unchecked")
  public <T> T getProp(@Nonnull String key) {
    final var value = props.get(key);
    if (value == null) {
      return null;
    }
    return (T) value;
  }

  @Setter
  private Throwable exception;
}
