package ru.rshb.intech.digital.feature.internship.core.service.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.repository.registration.RegistrationRepository;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageProcessorContext;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RegistrationCallbackTest {

  @Mock
  private RegistrationRepository registrationRepository;

  private MessageProcessorContext messageProcessorContext;

  private Registration registration;

  private RegistrationCallback.Before before;
  private RegistrationCallback.After after;

  @BeforeEach
  void setUp() {
    registration = new Registration();

    messageProcessorContext = MessageProcessorContext.builder()
        .build();

    before = new RegistrationCallback.Before(registration, registrationRepository);
    after = new RegistrationCallback.After(registrationRepository);
  }

  @Test
  void givenBefore_proceed_thenSuccess() {

    given(
        registrationRepository.save(registration)
    ).willReturn(registration);

    before.proceed(messageProcessorContext);
  }

  @Test
  void givenAfter_proceed_thenSuccess() {

    messageProcessorContext.getProps()
            .put("internship.registration", registration);

    after.proceed(messageProcessorContext);
  }

  @Test
  void givenAfterWithException_proceed_thenSuccess() {

    IllegalStateException expectedException;
    try {
      throw new IllegalStateException("Expected exception");
    } catch (IllegalStateException e) {
      expectedException = e;
    }

    messageProcessorContext.setException(expectedException);
    messageProcessorContext.getProps()
        .put("internship.registration", registration);

    after.proceed(messageProcessorContext);

    assertThat(
        isNotBlank(registration.getLog())
    ).isTrue();
  }
}