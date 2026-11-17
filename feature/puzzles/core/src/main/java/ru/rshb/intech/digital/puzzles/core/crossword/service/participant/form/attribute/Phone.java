package ru.rshb.intech.digital.puzzles.core.crossword.service.participant.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.phone.PhoneAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordParticipantRepository;
import ru.rshb.intech.digital.puzzles.core.crossword.service.participant.form.CrosswordParticipantForm;
import ru.rshb.intech.digital.puzzles.dto.CrosswordParticipantDto;
import ru.rshb.intech.digital.puzzles.entity.CrosswordParticipant;

@Component("crosswordParticipantFormPhone")
@FormAttribute(CrosswordParticipantForm.class)
public class Phone extends PhoneAttributeBase<CrosswordParticipantDto, CrosswordParticipant> {


  public Phone(@Nonnull CrosswordParticipantRepository participantRepository) {
    super(new DuplicatePhoneValidator(participantRepository));
  }


  @Override
  public String supplyInputValue(@Nonnull CrosswordParticipantDto inputData) {
    return inputData.getPhone();
  }

  @Override
  public void consumeOutputValue(String value, @Nonnull CrosswordParticipant outputData) {
    outputData.setPhone(value);
  }

  @Override
  public int processOrder() {
    return 3;
  }

  @RequiredArgsConstructor
  private static class DuplicatePhoneValidator
      implements BusinessRulesValidator<CrosswordParticipantDto, CrosswordParticipant, String> {

    private final CrosswordParticipantRepository participantRepository;

    @Override
    public void validate(String phone,
                         @Nonnull FormContext<CrosswordParticipantDto, CrosswordParticipant> context)
        throws AttributeBusinessRulesException {

      if (participantRepository.existsByPhone(phone)) {
        throw new AttributeBusinessRulesException(
            I18n.of("puzzle.crossword.error.validation.duplicate")
        );
      }
    }
  }
}
