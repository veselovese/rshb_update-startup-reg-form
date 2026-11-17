package ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form;

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
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowProposalFormDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowProposal;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.WindowProposalService;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.notification.WindowProposalNotification;

import java.util.List;

/**
 * Форма предложения по инновациям.
 */
@Component
public class WindowProposalForm extends Form<InnovationWindowProposalFormDto, InnovationWindowProposal> {

  public static final String TITLE_KEY = "innovations.proposal.form";

  private final WindowProposalService windowProposalService;
  private final WindowProposalNotification windowProposalNotification;


  public WindowProposalForm(@Nonnull FormAttributeProvider attributeProvider,
                            @Nonnull CensorAnalyzer censorAnalyzer,
                            @Nonnull WindowProposalService windowProposalService,
                            @Nonnull WindowProposalNotification windowProposalNotification) {

    super(attributeProvider, TITLE_KEY, new CensorPreCheck<>(censorAnalyzer));

    this.windowProposalService = windowProposalService;
    this.windowProposalNotification = windowProposalNotification;
  }

  @Nonnull
  @Override
  public InnovationWindowProposal process(@Nonnull InnovationWindowProposalFormDto inputData) {

    final var attachments = Attachments.list()
        .ofNonNull(inputData.getAttachment(), windowProposalService.getFormAttachmentAllowedExtensions())
        .collectNonNull();

    return process(
        inputData,
        InnovationWindowProposal::new,
        new ResultHandler(windowProposalService, windowProposalNotification, attachments)
    );
  }

  @RequiredArgsConstructor
  private static class ResultHandler
      implements FormResultHandler<InnovationWindowProposalFormDto, InnovationWindowProposal> {

    private final WindowProposalService windowProposalService;
    private final WindowProposalNotification windowProposalNotification;
    private final List<Attachment> attachments;

    @Nonnull
    @Override
    public InnovationWindowProposal handleResult(@Nonnull InnovationWindowProposal result,
                                                 @Nonnull FormContext<InnovationWindowProposalFormDto, InnovationWindowProposal> context) {

      if (context.is(PreCheckResult.SUSPICIOUS)) {
        result.setSuspicious(true);
      }

      final var savedProposal = windowProposalService.save(result);
      windowProposalNotification.send(savedProposal, attachments);

      return savedProposal;
    }
  }
}
