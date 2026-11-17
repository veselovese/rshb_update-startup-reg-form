package ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowProposalFormDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowProposal;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.WindowProposalForm;

/**
 * Атрибут "Описание предлагаемого решения".
 * Форма предложения по инновациям.
 */
@Component("windowProposalDescription")
@FormAttribute(WindowProposalForm.class)
public class Description extends TextAttributeBase<InnovationWindowProposalFormDto, InnovationWindowProposal> {

  public static final String TITLE_KEY = "innovations.proposal.form.attribute.description";

  public Description() {
    super(TITLE_KEY, InnovationWindowProposal.DESCRIPTION_LENGTH);
  }


  @Override
  public String supplyInputValue(@Nonnull InnovationWindowProposalFormDto inputData) {
    return inputData.getDescription();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull InnovationWindowProposal outputData) {
    outputData.setDescription(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
