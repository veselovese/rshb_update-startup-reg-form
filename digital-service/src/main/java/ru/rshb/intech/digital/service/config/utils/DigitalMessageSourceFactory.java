package ru.rshb.intech.digital.service.config.utils;

import jakarta.annotation.Nonnull;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;

public class DigitalMessageSourceFactory {

  public static final String MESSAGES_BUNDLE_NAME = "messages";

  @Nonnull
  public static MessageSource messageSource() {
    final var messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:" + MESSAGES_BUNDLE_NAME);
    messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
    return messageSource;
  }

}
