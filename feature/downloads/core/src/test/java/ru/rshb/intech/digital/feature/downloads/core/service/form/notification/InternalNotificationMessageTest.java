package ru.rshb.intech.digital.feature.downloads.core.service.form.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;
import ru.rshb.intech.digital.subsystem.content.files.entity.FileIndexEntry;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class InternalNotificationMessageTest {
  private InternalNotificationMessage internalNotificationMessage;

  @BeforeEach
  void setUp() {

    final var fileRequest = new FileRequest();
    fileRequest.setId(1L);
    fileRequest.setFullName("Иванов Иван Иванович");
    fileRequest.setEmail("test@test.te");
    fileRequest.setPhone("+79996664242");
    fileRequest.setPosition("Генеральный директор");
    fileRequest.setOrganization("ООО \"Тест\"");
    fileRequest.setSubmitDate(LocalDateTime.now());
    fileRequest.setSuspicious(false);

    final var file = new FileIndexEntry();
    file.setTitle("Тестовый файл");

    fileRequest.setFile(file);

    internalNotificationMessage = new InternalNotificationMessage(fileRequest);
  }

  @Test
  void getTopic() {
    assertThat(internalNotificationMessage.getTopic())
        .isEqualTo(InternalNotificationMessage.TOPIC_ID);
  }

  @Test
  void getSubject() {
    assertThat(internalNotificationMessage.getSubject())
        .isNotNull();
  }

  @Test
  void isUseDefaultSubject() {
    assertThat(internalNotificationMessage.isUseDefaultSubject())
        .isFalse();
  }

  @Test
  void getValues() {

    final Values reportValues = internalNotificationMessage.getValues();

    assertThat(reportValues)
        .isNotNull();

    assertThat(reportValues.size())
        .isEqualTo(InternalNotificationMessage.ReportAttributes.values().length);
  }

  @Test
  void givenValues_getKey_thenNotBlank() {
    for (final var entry : InternalNotificationMessage.ReportAttributes.values()) {
      assertThat(entry.getKey())
          .isNotNull();
    }
  }

  @Test
  void getAttachments() {
    assertThat(internalNotificationMessage.getAttachments())
        .isNotNull()
        .isEmpty();
  }
}