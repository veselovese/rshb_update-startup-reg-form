package ru.rshb.intech.digital.messages;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.ResourceBundle;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class I18n {

  private final static String MESSAGES_BUNDLE = "messages";
  private final static ResourceBundle messagesBundle = ResourceBundle.getBundle(MESSAGES_BUNDLE);


  @Nonnull
  public static String of(@Nonnull String key) {
    try {
      return messagesBundle.getString(key);
    } catch (Exception e) {
      return key;
    }
  }

  @Nonnull
  public static String of(@Nonnull String key, Object... args) {
    final var template = of(key);
    return MessageFormat.format(template, args);
  }

  @Nonnull
  public static String ofKeys(@Nonnull String msgKey, String... argKeys) {

    final var msg = of(msgKey);
    final var args = Arrays.stream(argKeys)
        .map(I18n::of)
        .toArray();

    return MessageFormat.format(msg, args);
  }
}
