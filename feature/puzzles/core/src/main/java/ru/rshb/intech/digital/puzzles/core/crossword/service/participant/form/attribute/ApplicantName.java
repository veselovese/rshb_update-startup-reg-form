package ru.rshb.intech.digital.puzzles.core.crossword.service.participant.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.applicant.ApplicantNameAttributeBase;
import ru.rshb.intech.digital.puzzles.core.crossword.service.participant.form.CrosswordParticipantForm;
import ru.rshb.intech.digital.puzzles.dto.CrosswordParticipantDto;
import ru.rshb.intech.digital.puzzles.entity.CrosswordParticipant;

@Component("crosswordParticipantFormApplicantName")
@FormAttribute(CrosswordParticipantForm.class)
public class ApplicantName extends ApplicantNameAttributeBase<CrosswordParticipantDto, CrosswordParticipant> {

  public static final String TITLE_KEY = "puzzle.crossword.participant.form.field.applicant.name";

  public ApplicantName() {
    super(TITLE_KEY, CrosswordParticipant.PARTICIPANT_NAME_LENGTH);
  }

  @Override
  public String supplyInputValue(@Nonnull CrosswordParticipantDto inputData) {
    return inputData.getApplicantName();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull CrosswordParticipant outputData) {
    outputData.setParticipantName(outputValue);
  }

  @Override
  public int processOrder() {
    return 2;
  }
}
