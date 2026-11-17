package ru.rshb.intech.digital.puzzles.core.crossword.service.participant.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.puzzles.core.crossword.service.participant.form.CrosswordParticipantForm;
import ru.rshb.intech.digital.puzzles.dto.CrosswordParticipantDto;
import ru.rshb.intech.digital.puzzles.entity.CrosswordParticipant;

@Component("crosswordParticipantFormDescription")
@FormAttribute(CrosswordParticipantForm.class)
public class Description extends TextAttributeBase<CrosswordParticipantDto, CrosswordParticipant> {

  public static final String TITLE_KEY = "puzzle.crossword.participant.form.field.about";

  public Description() {
    super(TITLE_KEY, true, CrosswordParticipant.ABOUT_LENGTH);
  }


  @Override
  public String supplyInputValue(@Nonnull CrosswordParticipantDto inputData) {
    return inputData.getDescription();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull CrosswordParticipant outputData) {
    outputData.setAbout(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
