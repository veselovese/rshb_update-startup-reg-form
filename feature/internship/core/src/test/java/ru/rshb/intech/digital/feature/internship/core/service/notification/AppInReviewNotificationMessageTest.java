package ru.rshb.intech.digital.feature.internship.core.service.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.notifications.internal.service.TemplatedEmailSender;

@ExtendWith(MockitoExtension.class)
class AppInReviewNotificationMessageTest {

  @Mock
  private TemplatedEmailSender templatedEmailSender;

  @InjectMocks
  private AppInReviewNotificationMessage appInReviewNotificationMessage;

  private Registration registration;

  @BeforeEach
  void setUp() {
    registration = new Registration();
  }

  @Test
  void givenRecipient_sendWith_thenSuccess() {

    registration.setEmail("test@test.te");

    appInReviewNotificationMessage.sendWith(registration);
  }
}