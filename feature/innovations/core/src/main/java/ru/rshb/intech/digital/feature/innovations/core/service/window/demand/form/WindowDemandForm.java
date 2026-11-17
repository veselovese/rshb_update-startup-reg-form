package ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form;

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
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowDemandFormDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowDemand;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.WindowDemandService;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.notification.WindowDemandNotification;

import java.util.List;

/**
 * Форма запроса на инновации.
 */
@Component
public class WindowDemandForm extends Form<InnovationWindowDemandFormDto, InnovationWindowDemand> {

  public static final String TITLE_KEY = "innovations.demand.form";


  private final WindowDemandService windowDemandService;
  private final WindowDemandNotification windowDemandNotification;


  public WindowDemandForm(@Nonnull FormAttributeProvider attributeProvider,
                          @Nonnull CensorAnalyzer censorAnalyzer,
                          @Nonnull WindowDemandService windowDemandService,
                          @Nonnull WindowDemandNotification windowDemandNotification) {

    super(attributeProvider, TITLE_KEY, new CensorPreCheck<>(censorAnalyzer));

    this.windowDemandService = windowDemandService;
    this.windowDemandNotification = windowDemandNotification;
  }


  @Nonnull
  @Override
  public InnovationWindowDemand process(@Nonnull InnovationWindowDemandFormDto inputData) {

    final var attachments = Attachments.list()
        .ofNonNull(inputData.getAttachment(), windowDemandService.getFormAttachmentAllowedExtensions())
        .collectNonNull();

    return process(
        inputData,
        InnovationWindowDemand::new,
        new ResultHandler(windowDemandService, windowDemandNotification, attachments)
    );
  }

  @RequiredArgsConstructor
  private static class ResultHandler
      implements FormResultHandler<InnovationWindowDemandFormDto, InnovationWindowDemand> {

    private final WindowDemandService windowDemandService;
    private final WindowDemandNotification windowDemandNotification;
    private final List<Attachment> attachments;

    @Nonnull
    @Override
    public InnovationWindowDemand handleResult(@Nonnull InnovationWindowDemand result,
                                               @Nonnull FormContext<InnovationWindowDemandFormDto, InnovationWindowDemand> context) {

      if (context.is(PreCheckResult.SUSPICIOUS)) {
        result.setSuspicious(true);
      }

      final var savedDemand = windowDemandService.save(result);
      windowDemandNotification.send(savedDemand, attachments);

      return savedDemand;
    }
  }
}
