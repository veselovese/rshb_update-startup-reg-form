package ru.rshb.intech.digital.feature.internship.core.service.registration.form;

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
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.service.registration.InternshipRegistrationNotification;
import ru.rshb.intech.digital.feature.internship.core.service.registration.RegistrationService;

import java.util.List;

/**
 * Форма подачи заявки на стажировку.
 */
@Component
public class InternshipRegistrationForm extends Form<InternshipRegistrationDto, Registration> {

  public static final String TITLE_KEY = "internship.registration.form.name";

  private final RegistrationService registrationService;
  private final InternshipRegistrationNotification registrationNotification;

  public InternshipRegistrationForm(@Nonnull FormAttributeProvider attributeProvider,
                                    @Nonnull CensorAnalyzer censorAnalyzer,
                                    @Nonnull RegistrationService registrationService,
                                    @Nonnull InternshipRegistrationNotification registrationNotification) {

    super(attributeProvider, TITLE_KEY, new CensorPreCheck<>(censorAnalyzer));

    this.registrationService = registrationService;
    this.registrationNotification = registrationNotification;
  }

  @Nonnull
  @Override
  public Registration process(@Nonnull InternshipRegistrationDto dto) {

    final var attachments = Attachments.list()
        .ofNonNull(dto.getAttachData(), registrationService.getFormAttachmentAllowedExtensions())
        .ofNullable(dto.getSkillPassportFile(), registrationService.getSkillPassportAllowedExtensions())
        .collectNonNull();

    return process(
        dto,
        Registration::new,
        new ResultHandler(
            attachments,
            registrationService,
            registrationNotification
        )
    );
  }

  @RequiredArgsConstructor
  private static class ResultHandler implements FormResultHandler<InternshipRegistrationDto, Registration> {

    private final List<Attachment> attachments;
    private final RegistrationService registrationService;
    private final InternshipRegistrationNotification registrationNotification;

    @Nonnull
    @Override
    public Registration handleResult(@Nonnull Registration registration,
                                     @Nonnull FormContext<InternshipRegistrationDto, Registration> context) {

      if (context.is(PreCheckResult.SUSPICIOUS)) {
        registration.setSuspicious(true);
      }

      final var savedRegistration = registrationService.save(registration);

      registrationNotification.send(savedRegistration, attachments);
      registrationNotification.sendApplicantNotification(savedRegistration);

      return savedRegistration;
    }
  }
}
