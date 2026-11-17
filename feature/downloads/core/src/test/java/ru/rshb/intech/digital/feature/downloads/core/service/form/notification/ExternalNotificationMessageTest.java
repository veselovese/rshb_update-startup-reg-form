package ru.rshb.intech.digital.feature.downloads.core.service.form.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;
import ru.rshb.intech.digital.notifications.entity.NotificationTemplate;
import ru.rshb.intech.digital.subsystem.content.files.entity.FileIndexEntry;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ExternalNotificationMessageTest {

  private ExternalNotificationMessage externalNotificationMessage;
  private FileRequest fileRequest;

  @BeforeEach
  void setUp() {

    fileRequest = new FileRequest();
    fileRequest.setId(1L);
    fileRequest.setFullName("Иванов Иван Иванович");
    fileRequest.setEmail("test@test.te");
    fileRequest.setPhone("+79996664242");
    fileRequest.setPosition("Генеральный директор");
    fileRequest.setOrganization("ООО \"Тест\"");
    fileRequest.setSubmitDate(LocalDateTime.now());
    fileRequest.setSuspicious(false);

    final var notificationTemplate = new NotificationTemplate();
    notificationTemplate.setKey("some.template.key");
    notificationTemplate.setDefaultSubject("Some Subject");

    final var file = new FileIndexEntry();
    file.setTitle("Тестовый файл");
    file.setExternalNotificationTemplate(notificationTemplate);

    fileRequest.setFile(file);

    externalNotificationMessage = new ExternalNotificationMessage(fileRequest);
  }

  @Test
  void getTemplateKey() {
    assertThat(externalNotificationMessage.getTemplateKey())
        .isEqualTo(fileRequest.getFile().getExternalNotificationTemplate().getKey());
  }

  @Test
  void getSubject() {
    assertThat(externalNotificationMessage.getSubject())
        .isEqualTo(fileRequest.getFile().getExternalNotificationTemplate().getDefaultSubject());
  }

  @Test
  void getValues() {

    final Values values = externalNotificationMessage.getValues();

    assertThat(values)
        .isNotNull();

    assertThat(values.get("downloadUrl"))
        .isEqualTo(fileRequest.getFile().getUrl());
  }

  @Test
  void getAttachments() {
    assertThat(externalNotificationMessage.getAttachments())
        .isNotNull()
        .isEmpty();
  }
}