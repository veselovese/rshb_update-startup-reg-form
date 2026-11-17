package ru.rshb.intech.digital.subscriptions.core.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.rshb.intech.digital.notifications.api.NotificationService;
import ru.rshb.intech.digital.subscriptions.core.exception.SubscriptionException;
import ru.rshb.intech.digital.subscriptions.core.service.activation.ActivationEmailTemplate;
import ru.rshb.intech.digital.subscriptions.core.service.activation.DefaultActivationLinkFactory;
import ru.rshb.intech.digital.subscriptions.core.service.activation.UuidActivationCodeFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@Disabled
@SpringBootTest(
    classes = {
        MailNotificationServiceImpl.class,
        DefaultActivationLinkFactory.class,
        UuidActivationCodeFactory.class
    }
)
class MailNotificationServiceImplTest {

  @Autowired
  private MailNotificationService mailNotificationService;

  @MockBean
  private ActivationEmailTemplate activationEmailTemplate;

  @MockBean
  private NotificationService notificationService;

  @Test
  void sendActivationLetter() {

    when(activationEmailTemplate.toHtml(any()))
        .thenReturn("Lorem ipsum");

    doThrow(IllegalStateException.class)
        .when(notificationService).send(any());

    assertThrows(SubscriptionException.class, () -> {
      mailNotificationService.sendActivationLetter("none@none.com", "8ccbd91f-7da1-4248-a286-bc48a9c64414");
    });
  }
}