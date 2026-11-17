package ru.rshb.intech.digital.feature.patents.core.service.publication.form;

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
import ru.rshb.intech.digital.feature.patents.core.dto.PatentPublicationDto;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.service.PatentPublicationService;
import ru.rshb.intech.digital.feature.patents.core.service.publication.notification.PatentPublicationNotification;

import java.util.List;

/**
 * Форма заявки на размещение патента в витрине патентов.
 */
@Component
public class PatentPublicationForm extends Form<PatentPublicationDto, PatentPublication> {

  public static final String TITLE_KEY = "patents.showcase.publication.form.name";

  private final PatentPublicationService patentPublicationService;
  private final PatentPublicationNotification patentPublicationNotification;

  public PatentPublicationForm(@Nonnull FormAttributeProvider attributeProvider,
                               @Nonnull CensorAnalyzer censorAnalyzer,
                               @Nonnull PatentPublicationService patentPublicationService,
                               @Nonnull PatentPublicationNotification patentPublicationNotification) {
    super(attributeProvider, TITLE_KEY, new CensorPreCheck<>(censorAnalyzer));

    this.patentPublicationService = patentPublicationService;
    this.patentPublicationNotification = patentPublicationNotification;
  }

  @Nonnull
  @Override
  public PatentPublication process(@Nonnull PatentPublicationDto inputData) {

    final var attachments = Attachments.list()
        .ofNullable(inputData.getAttachmentLogo(), patentPublicationService.getLogoAllowedExtensions())
        .ofNullable(inputData.getAttachmentPresentation(), patentPublicationService.getPresentationAllowedExtensions())
        .collectNonNull();

    return process(
        inputData,
        PatentPublication::new,
        new ResultHandler(
            patentPublicationService,
            patentPublicationNotification,
            attachments
        )
    );
  }

  @RequiredArgsConstructor
  private static class ResultHandler implements FormResultHandler<PatentPublicationDto, PatentPublication> {

    private final PatentPublicationService patentPublicationService;
    private final PatentPublicationNotification patentPublicationNotification;
    private final List<Attachment> attachments;

    @Nonnull
    @Override
    public PatentPublication handleResult(@Nonnull PatentPublication publication,
                                          @Nonnull FormContext<PatentPublicationDto, PatentPublication> context) {

      if (context.is(PreCheckResult.SUSPICIOUS)) {
        publication.setSuspicious(true);
      }

      final var savedPublication = patentPublicationService.save(publication);

      patentPublicationNotification.send(savedPublication, attachments);

      return savedPublication;
    }
  }
}
