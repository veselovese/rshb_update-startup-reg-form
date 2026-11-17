package ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.content.provider.api.InnovationContentService;
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowProposalFormDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowProposal;
import ru.rshb.intech.digital.feature.innovations.core.exception.ProposalValidationException;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.WindowProposalForm;

/**
 * Суррогатный атрибут "ID запроса на инновацию".
 * Форма предложения по инновациям.
 */
@Component("windowProposalDemandId")
@FormAttribute(WindowProposalForm.class)
public class DemandId extends TextAttributeBase<InnovationWindowProposalFormDto, InnovationWindowProposal> {

  public static final String TITLE_KEY = "innovations.proposal.form.attribute.demand.id";
  private static final int MAX_LENGTH = 100;
  private static final int PROCESS_ORDER = 1;

  public DemandId(@Nonnull InnovationContentService innovationContentService) {
    super(TITLE_KEY, MAX_LENGTH, new DuplicateValidator(innovationContentService));
  }


  @Override
  public String supplyInputValue(@Nonnull InnovationWindowProposalFormDto inputData) {
    return inputData.getDemandCardId();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull InnovationWindowProposal outputData) {
    outputData.setDemandCardId(outputValue);
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }

  @RequiredArgsConstructor
  private static class DuplicateValidator
      implements BusinessRulesValidator<InnovationWindowProposalFormDto, InnovationWindowProposal, String> {

    private final InnovationContentService innovationContentService;

    @Override
    public void validate(String demandCardId,
                         @Nonnull FormContext<InnovationWindowProposalFormDto, InnovationWindowProposal> context)
        throws AttributeBusinessRulesException {

      if (!innovationContentService.isExistById(demandCardId)) {
        throw ProposalValidationException.demandNotFound(demandCardId);
      }
    }
  }
}
