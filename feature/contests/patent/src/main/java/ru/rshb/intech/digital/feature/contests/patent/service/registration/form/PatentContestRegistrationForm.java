package ru.rshb.intech.digital.feature.contests.patent.service.registration.form;

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
import ru.rshb.intech.digital.feature.contests.patent.dto.PatentContestRegistrationRequestDto;
import ru.rshb.intech.digital.feature.contests.patent.entity.PatentContestRegistration;
import ru.rshb.intech.digital.feature.contests.patent.service.PatentContestRegistrationService;
import ru.rshb.intech.digital.feature.contests.patent.service.registration.notification.PatentContestRegistrationNotification;

import java.util.List;

/**
 * Форма регистрации участника в конкурсе патентов.
 */
@Component
public class PatentContestRegistrationForm extends Form<PatentContestRegistrationRequestDto, PatentContestRegistration> {

  public static final String TITLE_KEY = "patents.contest.registration.form.name";

  private final PatentContestRegistrationService registrationService;
  private final PatentContestRegistrationNotification registrationNotification;


  public PatentContestRegistrationForm(@Nonnull FormAttributeProvider attributeProvider,
                                       @Nonnull CensorAnalyzer censorAnalyzer,
                                       @Nonnull PatentContestRegistrationService registrationService,
                                       @Nonnull PatentContestRegistrationNotification registrationNotification) {

    super(attributeProvider, TITLE_KEY, new CensorPreCheck<>(censorAnalyzer));

    this.registrationService = registrationService;
    this.registrationNotification = registrationNotification;
  }

  @Nonnull
  @Override
  public PatentContestRegistration process(@Nonnull PatentContestRegistrationRequestDto inputData) {

    final var attachments = Attachments.list()
        .ofNullable(inputData.getAttachmentLogo(), registrationService.getLogoSupportedExtensions())
        .ofNullable(inputData.getAttachmentPresentation(), registrationService.getPresentationSupportedExtensions())
        .collectNonNull();

    return process(
        inputData,
        PatentContestRegistration::new,
        new ResultHandler(attachments, registrationService, registrationNotification)
    );
  }

  @RequiredArgsConstructor
  private static class ResultHandler implements FormResultHandler<PatentContestRegistrationRequestDto, PatentContestRegistration> {

    private final List<Attachment> attachments;
    private final PatentContestRegistrationService registrationService;
    private final PatentContestRegistrationNotification registrationNotification;

    @Nonnull
    @Override
    public PatentContestRegistration handleResult(@Nonnull PatentContestRegistration result,
                                                  @Nonnull FormContext<PatentContestRegistrationRequestDto, PatentContestRegistration> context) {

      if (context.is(PreCheckResult.SUSPICIOUS)) {
        result.setSuspicious(true);
      }

      final var savedRegistration = registrationService.save(result);

      registrationNotification.send(savedRegistration, attachments);

      return savedRegistration;
    }
  }
}
