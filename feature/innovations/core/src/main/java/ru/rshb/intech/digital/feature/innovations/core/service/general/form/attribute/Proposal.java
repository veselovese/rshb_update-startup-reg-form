package ru.rshb.intech.digital.feature.innovations.core.service.general.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.innovations.core.dto.GeneralRequestDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.GeneralRequest;
import ru.rshb.intech.digital.feature.innovations.core.service.general.form.GeneralRequestForm;

/**
 * Атрибут "Предложение или запрос".
 * Форма заявки с предложением или запросом инноваций.
 */
@Component("innovationsGeneralRequestFormProposal")
@FormAttribute(GeneralRequestForm.class)
public class Proposal extends TextAttributeBase<GeneralRequestDto, GeneralRequest> {

  public static final String TITLE_KEY = "innovations.general.request.form.attribute.proposal";

  public Proposal() {
    super(TITLE_KEY, GeneralRequest.PROPOSAL_LENGTH);
  }


  @Override
  public String supplyInputValue(@Nonnull GeneralRequestDto inputData) {
    return inputData.getProposal();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull GeneralRequest outputData) {
    outputData.setProposal(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
