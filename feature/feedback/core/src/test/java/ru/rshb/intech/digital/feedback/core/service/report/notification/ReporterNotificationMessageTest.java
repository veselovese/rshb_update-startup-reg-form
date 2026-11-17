package ru.rshb.intech.digital.feedback.core.service.report.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReporterNotificationMessageTest {

  private ReporterNotificationMessage reporterNotificationMessage;

  @BeforeEach
  void setUp() {
    reporterNotificationMessage = new ReporterNotificationMessage();
  }

  @Test
  void getTemplateKey() {
    assertThat(
        reporterNotificationMessage.getTemplateKey()
    ).isNotBlank();
  }

  @Test
  void getSubject() {
    assertThat(
        reporterNotificationMessage.getSubject()
    ).isNull();
  }

  @Test
  void getValues() {
    assertThat(
        reporterNotificationMessage.getValues()
    ).isNotNull();
  }

  @Test
  void getAttachments() {
    assertThat(
        reporterNotificationMessage.getAttachments()
    ).isNotNull();
  }
}
