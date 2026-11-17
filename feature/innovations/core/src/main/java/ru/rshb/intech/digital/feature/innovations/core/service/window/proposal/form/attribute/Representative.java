package ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.applicant.ApplicantNameAttributeBase;
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowProposalFormDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowProposal;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.WindowProposalForm;

/**
 * Атрибут "ФИО".
 * Форма предложения по инновациям.
 */
@Component("windowProposalRepresentative")
@FormAttribute(WindowProposalForm.class)
public class Representative
    extends ApplicantNameAttributeBase<InnovationWindowProposalFormDto, InnovationWindowProposal> {

  public Representative() {
    super(InnovationWindowProposal.REPRESENTATIVE_LENGTH);
  }

  @Override
  public String supplyInputValue(@Nonnull InnovationWindowProposalFormDto inputData) {
    return inputData.getRepresentativeName();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull InnovationWindowProposal outputData) {
    outputData.setRepresentativeName(outputValue);
  }
}
