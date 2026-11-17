package ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.email.EmailAttributeBase;
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowProposalFormDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowProposal;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.WindowProposalForm;

/**
 * Атрибут "Электронная почта".
 * Форма предложения по инновациям.
 */
@Component("windowProposalEmail")
@FormAttribute(WindowProposalForm.class)
public class Email extends EmailAttributeBase<InnovationWindowProposalFormDto, InnovationWindowProposal> {

  private static final int PROCESS_ORDER = 2;

  @Override
  public String supplyInputValue(@Nonnull InnovationWindowProposalFormDto inputData) {
    return inputData.getEmail();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull InnovationWindowProposal outputData) {
    outputData.setEmail(outputValue);
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }
}
