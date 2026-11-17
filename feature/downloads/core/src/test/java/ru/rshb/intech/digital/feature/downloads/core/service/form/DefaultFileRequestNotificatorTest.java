package ru.rshb.intech.digital.feature.downloads.core.service.form;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;
import ru.rshb.intech.digital.notifications.entity.NotificationTemplate;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;
import ru.rshb.intech.digital.notifications.internal.service.TemplatedEmailSender;
import ru.rshb.intech.digital.subsystem.content.files.entity.FileIndexEntry;

@ExtendWith(MockitoExtension.class)
class DefaultFileRequestNotificatorTest {

  private DefaultFileRequestNotificator notificator;

  @Mock
  private InternalNotificationService internalNotificationService;

  @Mock
  private TemplatedEmailSender templatedEmailSender;

  @BeforeEach
  void setUp() {
    this.notificator = new DefaultFileRequestNotificator(internalNotificationService, templatedEmailSender);
  }

  @Test
  void givenValid_sendInternalNotification_thenSuccess() {

    final var fileEntry = new FileIndexEntry();
    fileEntry.setTitle("Тестовый файл");

    final var fileRequest = new FileRequest();
    fileRequest.setFile(fileEntry);

    notificator.sendInternalNotification(fileRequest);
  }

  @Test
  void givenValid_sendExternalNotification_thenOk() {

    final var notificationTemplate = new NotificationTemplate();

    final var fileEntry = new FileIndexEntry();
    fileEntry.setTitle("Тестовый файл");
    fileEntry.setExternalNotificationTemplate(notificationTemplate);

    final var fileRequest = new FileRequest();
    fileRequest.setEmail("test@test.te");
    fileRequest.setFile(fileEntry);

    notificator.sendExternalNotification(fileRequest);
  }

  @Test
  void givenWithoutTemplate_sendExternalNotification_thenOk() {

    final var fileEntry = new FileIndexEntry();
    fileEntry.setTitle("Тестовый файл");
    fileEntry.setExternalNotificationTemplate(null);

    final var fileRequest = new FileRequest();
    fileRequest.setEmail("test@test.te");
    fileRequest.setFile(fileEntry);

    notificator.sendExternalNotification(fileRequest);
  }

  @Test
  void givenValid_sendNotification_thenSuccess() {

    final var fileEntry = new FileIndexEntry();
    fileEntry.setTitle("Тестовый файл");

    final var fileRequest = new FileRequest();
    fileRequest.setFile(fileEntry);

    notificator.sendNotification(fileRequest);
  }
}