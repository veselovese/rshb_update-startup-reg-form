package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form;

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
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationFormData;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.ExpertRegistrationService;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.notification.ExpertRegistrationNotification;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Форма регистрации "Стать экспертом".
 */
@Component
public class ExpertRegistrationForm extends Form<ExpertRegistrationFormData, ExpertRegistration> {

  public static final String TITLE_KEY = "expert.registration.form.title";

  private static final String[] ALLOWED_EXTENSIONS = {"ppt", "pptx", "doc", "docx", "pdf"};

  private final ExpertRegistrationService registrationService;
  private final ExpertRegistrationNotification registrationNotification;

  public ExpertRegistrationForm(@Nonnull FormAttributeProvider attributeProvider,
                                @Nonnull CensorAnalyzer censorAnalyzer,
                                @Nonnull ExpertRegistrationService registrationService,
                                @Nonnull ExpertRegistrationNotification registrationNotification) {

    super(attributeProvider, TITLE_KEY, new CensorPreCheck<>(censorAnalyzer));

    this.registrationService = registrationService;
    this.registrationNotification = registrationNotification;
  }

  @Nonnull
  @Override
  public ExpertRegistration process(@Nonnull ExpertRegistrationFormData inputData) {

    final var attachments = Stream.of(
          Attachment.fromNullableMultipartFile(inputData.getAttachment(), ALLOWED_EXTENSIONS)
        )
        .filter(Objects::nonNull)
        .toList();

    return process(
        inputData,
        ExpertRegistration::new,
        new ResultHandler(attachments, registrationService, registrationNotification)
    );
  }

  @RequiredArgsConstructor
  private static class ResultHandler implements FormResultHandler<ExpertRegistrationFormData, ExpertRegistration> {

    private final List<Attachment> attachments;
    private final ExpertRegistrationService registrationService;
    private final ExpertRegistrationNotification registrationNotification;

    @Nonnull
    @Override
    public ExpertRegistration handleResult(@Nonnull ExpertRegistration registration,
                                           @Nonnull FormContext<ExpertRegistrationFormData, ExpertRegistration> context) {

      if (context.is(PreCheckResult.SUSPICIOUS)) {
        registration.setSuspicious(true);
      }

      final var savedRegistration = registrationService.save(registration);

      registrationNotification.send(savedRegistration, attachments);

      return savedRegistration;
    }
  }
}
