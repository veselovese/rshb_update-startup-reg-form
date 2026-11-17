package ru.rshb.intech.digital.subscriptions.core.test;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.rshb.intech.digital.notifications.mail.template.HtmlTemplateConverter;

@TestConfiguration
public class TestConfig {

  @Bean
  public HtmlTemplateConverter mailTemplateConverter() {
    return new HtmlTemplateConverter();
  }
}
