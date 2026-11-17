package ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowProposalFormDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowProposal;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.WindowProposalForm;

/**
 * Атрибут "Наименование стартапа".
 * Форма предложения по инновациям.
 */
@Component("windowProposalStartupName")
@FormAttribute(WindowProposalForm.class)
public class StartupName extends TextAttributeBase<InnovationWindowProposalFormDto, InnovationWindowProposal> {

  public static final String TITLE_KEY = "innovations.proposal.form.attribute.startup.name";

  public StartupName() {
    super(TITLE_KEY, InnovationWindowProposal.STARTUP_NAME_LENGTH);
  }

  @Override
  public String supplyInputValue(@Nonnull InnovationWindowProposalFormDto inputData) {
    return inputData.getStartupName();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull InnovationWindowProposal outputData) {
    outputData.setStartupName(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
