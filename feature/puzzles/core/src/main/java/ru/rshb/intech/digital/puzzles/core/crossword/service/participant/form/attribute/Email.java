package ru.rshb.intech.digital.puzzles.core.crossword.service.participant.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.email.EmailAttributeBase;
import ru.rshb.intech.digital.puzzles.core.crossword.service.participant.form.CrosswordParticipantForm;
import ru.rshb.intech.digital.puzzles.dto.CrosswordParticipantDto;
import ru.rshb.intech.digital.puzzles.entity.CrosswordParticipant;

@Component("crosswordParticipantFormEmail")
@FormAttribute(CrosswordParticipantForm.class)
public class Email extends EmailAttributeBase<CrosswordParticipantDto, CrosswordParticipant> {

  @Override
  public String supplyInputValue(@Nonnull CrosswordParticipantDto inputData) {
    return inputData.getEmail();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull CrosswordParticipant outputData) {
    outputData.setEmail(outputValue);
  }
}
