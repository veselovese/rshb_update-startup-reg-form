package ru.rshb.intech.digital.feature.startups.core.service.registration.form;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.censor.forms.CensorAnalyzer;
import ru.rshb.intech.digital.censor.forms.CensorPreCheck;
import ru.rshb.intech.digital.commons.forms.Form;
import ru.rshb.intech.digital.commons.forms.FormResultHandler;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckResult;
import ru.rshb.intech.digital.commons.forms.attributes.provider.FormAttributeProvider;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.commons.utils.attachment.Attachments;
import ru.rshb.intech.digital.feature.startups.core.service.registration.StartupRegistrationService;
import ru.rshb.intech.digital.feature.startups.core.service.registration.form.notification.StartupRegistrationNotification;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegistrationDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;

import java.util.List;

/**
 * Форма заявки на включение стартапа в витрину.
 */
@Component
public class StartupRegistrationForm extends Form<StartupRegistrationDto, StartupRegistration> {

  public static final String TITLE_KEY = "startups.registration.form.name";

  private final StartupRegistrationService registrationService;
  private final StartupRegistrationNotification registrationNotification;


  public StartupRegistrationForm(@Nonnull FormAttributeProvider attributeProvider,
                                 @Nonnull CensorAnalyzer censorAnalyzer,
                                 @Nonnull StartupRegistrationService registrationService,
                                 @Nonnull StartupRegistrationNotification registrationNotification) {

    super(attributeProvider, TITLE_KEY, new CensorPreCheck<>(censorAnalyzer));
    this.registrationService = registrationService;
    this.registrationNotification = registrationNotification;
  }

  @Nonnull
  @Override
  public StartupRegistration process(@Nonnull StartupRegistrationDto inputData) {
    final var attachments = Attachments.list()
        .ofNullable(inputData.getAttachmentLogo(), registrationService.getLogoAllowedExtensions())
        .collectNonNull();

    return process(
        inputData,
        StartupRegistration::new,
        new ResultHandler(registrationService, registrationNotification, attachments)
    );
  }

  @RequiredArgsConstructor
  private static class ResultHandler implements FormResultHandler<StartupRegistrationDto, StartupRegistration> {

    private final StartupRegistrationService registrationService;
    private final StartupRegistrationNotification notification;
    private final List<Attachment> attachments;

    @Nonnull
    @Override
    public StartupRegistration handleResult(@Nonnull StartupRegistration registration,
                                            @Nonnull FormContext<StartupRegistrationDto, StartupRegistration> context) {

      if (context.is(PreCheckResult.SUSPICIOUS)) {
        registration.setSuspicious(true);
      }

      final var savedRegistration = registrationService.save(registration);

      notification.send(savedRegistration, attachments);

      return savedRegistration;
    }
  }
}
