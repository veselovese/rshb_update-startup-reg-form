package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.censor.forms.CensorAnalyzer;
import ru.rshb.intech.digital.censor.forms.CensorPreCheck;
import ru.rshb.intech.digital.commons.forms.Form;
import ru.rshb.intech.digital.commons.forms.FormResultHandler;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckResult;
import ru.rshb.intech.digital.commons.forms.attributes.provider.FormAttributeProvider;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.AgrotechOlympiadRegistrationDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadRegistration;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.AgrotechOlympiadRegistrationService;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.notification.AgrotechOlympiadRegistrationNotification;

/**
 * Форма заявки на участие в олимпиаде "Навстречу Агротеху".
 */
@Slf4j
@Component
public class AgrotechOlympiadRegistrationForm
    extends Form<AgrotechOlympiadRegistrationDto, AgrotechOlympiadRegistration>
    implements FormResultHandler<AgrotechOlympiadRegistrationDto, AgrotechOlympiadRegistration> {

  public static final String TITLE_KEY = "agrotech.olympiad.registration.form.name";

  private final AgrotechOlympiadRegistrationService registrationService;
  private final AgrotechOlympiadRegistrationNotification notification;

  public AgrotechOlympiadRegistrationForm(FormAttributeProvider attributeProvider,
                                          CensorAnalyzer censorAnalyzer,
                                          AgrotechOlympiadRegistrationService registrationService,
                                          AgrotechOlympiadRegistrationNotification notification) {

    super(attributeProvider, TITLE_KEY, new CensorPreCheck<>(censorAnalyzer));

    this.registrationService = registrationService;
    this.notification = notification;
  }

  @Nonnull
  @Override
  public AgrotechOlympiadRegistration process(@Nonnull AgrotechOlympiadRegistrationDto inputData) {
    return process(inputData, AgrotechOlympiadRegistration::new, this);
  }

  @Nonnull
  @Override
  public AgrotechOlympiadRegistration handleResult(@Nonnull AgrotechOlympiadRegistration registration,
                                                   @Nonnull FormContext<AgrotechOlympiadRegistrationDto, AgrotechOlympiadRegistration> context) {

    if (context.is(PreCheckResult.SUSPICIOUS)) {
      registration.setSuspicious(true);
    }

    final var savedRegistration = registrationService.save(registration);

    notification.send(savedRegistration);

    return savedRegistration;
  }
}
