package ru.rshb.intech.digital.feature.innovations.core.service.general.form;

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
import ru.rshb.intech.digital.feature.innovations.core.dto.GeneralRequestDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.GeneralRequest;
import ru.rshb.intech.digital.feature.innovations.core.service.general.GeneralRequestService;
import ru.rshb.intech.digital.feature.innovations.core.service.general.notification.GeneralRequestNotification;

import java.util.List;

/**
 * Форма заявки с предложением или запросом инноваций.
 */
@Component
public class GeneralRequestForm extends Form<GeneralRequestDto, GeneralRequest> {

  public static final String TITLE_KEY = "innovations.general.request.form";

  private final GeneralRequestService generalRequestService;
  private final GeneralRequestNotification generalRequestNotification;


  public GeneralRequestForm(@Nonnull FormAttributeProvider attributeProvider,
                            @Nonnull GeneralRequestService generalRequestService,
                            @Nonnull GeneralRequestNotification generalRequestNotification,
                            @Nonnull CensorAnalyzer censorAnalyzer) {

    super(attributeProvider, TITLE_KEY, new CensorPreCheck<>(censorAnalyzer));

    this.generalRequestService = generalRequestService;
    this.generalRequestNotification = generalRequestNotification;
  }

  @Nonnull
  @Override
  public GeneralRequest process(@Nonnull GeneralRequestDto inputData) {

    final var attachments = Attachments.list()
        .ofNullable(inputData.getAttachment(), generalRequestService.getFormAttachmentAllowedExtensions())
        .collectNonNull();

    return process(
        inputData,
        GeneralRequest::new,
        new ResultHandler(generalRequestService, generalRequestNotification, attachments)
    );
  }

  @RequiredArgsConstructor
  private static class ResultHandler implements FormResultHandler<GeneralRequestDto, GeneralRequest> {

    private final GeneralRequestService generalRequestService;
    private final GeneralRequestNotification generalRequestNotification;
    private final List<Attachment> attachments;

    @Nonnull
    @Override
    public GeneralRequest handleResult(@Nonnull GeneralRequest result,
                                       @Nonnull FormContext<GeneralRequestDto, GeneralRequest> context) {

      if (context.is(PreCheckResult.SUSPICIOUS)) {
        result.setSuspicious(true);
      }

      final var savedRequest = generalRequestService.save(result);
      generalRequestNotification.send(savedRequest, attachments);

      return savedRequest;
    }
  }
}
