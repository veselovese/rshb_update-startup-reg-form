package ru.rshb.intech.digital.feature.contests.chef.service.contestant.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.number.IntegerAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.contests.chef.dto.ChefContestantFormData;
import ru.rshb.intech.digital.feature.contests.chef.entity.ChefContestant;
import ru.rshb.intech.digital.feature.contests.chef.exception.ChefContestValidationException;
import ru.rshb.intech.digital.feature.contests.chef.service.ChefContestResultValidator;
import ru.rshb.intech.digital.feature.contests.chef.service.contestant.form.ChefContestantForm;

/**
 * Атрибут "Количество очков".
 * Форма участника конкурса "Цифровой повар".
 */
@Component("chefContestFormLevel")
@FormAttribute(ChefContestantForm.class)
public class Level extends IntegerAttributeBase<ChefContestantFormData, ChefContestant> {

  private static final String TITLE_KEY = "contest.chef.participant.form.attribute.level";
  private static final int MIN_VALUE = 0;
  private static final int PROCESS_ORDER = 2;

  private final ChefContestResultValidator resultValidator;


  public Level(@Nonnull ChefContestResultValidator resultValidator) {
    super(TITLE_KEY, MIN_VALUE, new LevelValueValidator(resultValidator));
    this.resultValidator = resultValidator;
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }

  @Override
  public Integer supplyInputValue(@Nonnull ChefContestantFormData inputData) {
    return inputData.getLevel();
  }

  @Override
  public void consumeOutputValue(Integer scores, @Nonnull ChefContestant outputData) {
    final var result = resultValidator.calcResultInPercents(scores);
    outputData.setResult(String.valueOf(result));
  }

  @RequiredArgsConstructor
  private static class LevelValueValidator
      implements BusinessRulesValidator<ChefContestantFormData, ChefContestant, Integer> {

    private static final int MIN_LEVEL = 3;
    private static final int MAX_LEVEL = 5;


    private final ChefContestResultValidator resultValidator;

    @Override
    public void validate(Integer value,
                         @Nonnull FormContext<ChefContestantFormData, ChefContestant> context)
        throws AttributeBusinessRulesException {

      final var contestant = context.getOutputData();

      final int providedScores = value;
      final int actualScores = resultValidator.getCheckLevel(contestant.getSolutionUrl());

      if (providedScores < MIN_LEVEL || providedScores > MAX_LEVEL) {
        throw ChefContestValidationException.invalidScoresAmount();
      }

      if (providedScores != actualScores) {
        throw ChefContestValidationException.scoresNotMatch();
      }
    }
  }
}
