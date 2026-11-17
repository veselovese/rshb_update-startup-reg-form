package ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowProposalFormDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowProposal;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.WindowProposalForm;

/**
 * Атрибут "Ссылка на стартап в витрине стартапов".
 * Форма предложения по инновациям.
 */
@Component("windowProposalStartupLink")
@FormAttribute(WindowProposalForm.class)
public class StartupLink extends TextAttributeBase<InnovationWindowProposalFormDto, InnovationWindowProposal> {

  public static final String TITLE_KEY = "innovations.proposal.form.attribute.startup.link";

  public StartupLink() {
    super(TITLE_KEY, true, InnovationWindowProposal.STARTUP_LINK_LENGTH);
  }

  @Override
  public String supplyInputValue(@Nonnull InnovationWindowProposalFormDto inputData) {
    return inputData.getStartupLink();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull InnovationWindowProposal outputData) {
    outputData.setStartupLink(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
