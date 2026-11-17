package ru.rshb.intech.digital.feature.internship.core.service.notification;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.transaction.annotation.Transactional;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.repository.registration.RegistrationRepository;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageCallback;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageProcessorContext;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationCallback {

  private static final String SUBMIT_STATUS_SUCCESS = "SUCCESS";
  private static final String REGISTRATION_CONTEXT_PROP = "internship.registration";


  @RequiredArgsConstructor
  public static class Before implements MessageCallback {

    private final Registration registration;
    private final RegistrationRepository registrationRepository;

    @Override
    @Transactional
    public void proceed(@Nonnull MessageProcessorContext context) {
      final var savedRegistration = registrationRepository.save(registration);
      context.getProps()
          .put(REGISTRATION_CONTEXT_PROP, savedRegistration);
    }
  }

  @RequiredArgsConstructor
  public static class After implements MessageCallback {

    private final RegistrationRepository registrationRepository;

    @Override
    public void proceed(@Nonnull MessageProcessorContext context) {

      final var status = (context.getException() == null)
          ? SUBMIT_STATUS_SUCCESS
          : ExceptionUtils.getStackTrace(context.getException());

      final var registration = context.<Registration>getProp(REGISTRATION_CONTEXT_PROP);
      registration.setLog(StringUtils.left(status, Registration.LOG_MAX_LEN));
      registrationRepository.save(registration);
    }
  }
}
