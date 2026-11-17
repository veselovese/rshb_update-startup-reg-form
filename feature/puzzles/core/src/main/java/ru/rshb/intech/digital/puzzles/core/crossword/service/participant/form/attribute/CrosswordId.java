package ru.rshb.intech.digital.puzzles.core.crossword.service.participant.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.Attribute;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordRepository;
import ru.rshb.intech.digital.puzzles.core.crossword.service.participant.form.CrosswordParticipantForm;
import ru.rshb.intech.digital.puzzles.dto.CrosswordParticipantDto;
import ru.rshb.intech.digital.puzzles.entity.CrosswordParticipant;

@Component("crosswordParticipantFormCrosswordId")
@FormAttribute(CrosswordParticipantForm.class)
public class CrosswordId extends Attribute<CrosswordParticipantDto, String, CrosswordParticipant, String> {


  public CrosswordId(@Nonnull CrosswordRepository crosswordRepository) {
    super(
        AttributeParams.Builder
            .<CrosswordParticipantDto, String, CrosswordParticipant, String>create()
            .name("crosswordId")
            .valueMapper(new TextAttributeBase.ThroughValueMapper())
            .businessRulesValidator(new CrosswordIdValidator(crosswordRepository))
            .build()
    );
  }


  @Override
  public String supplyInputValue(@Nonnull CrosswordParticipantDto inputData) {
    return inputData.getCrosswordId();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull CrosswordParticipant outputData) {
  }

  @Override
  public int processOrder() {
    return 1;
  }

  @RequiredArgsConstructor
  private static class CrosswordIdValidator
      implements BusinessRulesValidator<CrosswordParticipantDto, CrosswordParticipant, String> {

    private final CrosswordRepository crosswordRepository;

    @Override
    public void validate(String crosswordId,
                         @Nonnull FormContext<CrosswordParticipantDto, CrosswordParticipant> context)
        throws AttributeBusinessRulesException {

      if (!crosswordRepository.existsByExternalId(crosswordId)) {
        throw new AttributeBusinessRulesException(
            I18n.of("puzzle.crossword.error.validation.crossword.not.found", crosswordId)
        );
      }
    }
  }
}
