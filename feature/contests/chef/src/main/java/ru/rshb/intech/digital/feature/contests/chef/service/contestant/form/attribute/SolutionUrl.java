package ru.rshb.intech.digital.feature.contests.chef.service.contestant.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.contests.chef.dto.ChefContestantFormData;
import ru.rshb.intech.digital.feature.contests.chef.entity.ChefContestant;
import ru.rshb.intech.digital.feature.contests.chef.exception.ChefContestValidationException;
import ru.rshb.intech.digital.feature.contests.chef.service.ChefContestService;
import ru.rshb.intech.digital.feature.contests.chef.service.contestant.form.ChefContestantForm;

/**
 * Атрибут "URL решения".
 * Форма участника конкурса "Цифровой повар".
 */
@Component("chefContestFormSolutionUrl")
@FormAttribute(ChefContestantForm.class)
public class SolutionUrl extends TextAttributeBase<ChefContestantFormData, ChefContestant> {

  private static final String TITLE_KEY = "contest.chef.participant.form.attribute.solution.url";
  private static final int PROCESS_ORDER = 1;


  public SolutionUrl(@Nonnull ChefContestService chefContestService) {
    super(
        TITLE_KEY,
        ChefContestant.SOLUTION_URL_LENGTH,
        new SolutionDuplicateValidator(chefContestService)
    );
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }

  @Override
  public String supplyInputValue(@Nonnull ChefContestantFormData inputData) {
    return inputData.getUrl();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull ChefContestant outputData) {
    outputData.setSolutionUrl(outputValue);
  }

  @RequiredArgsConstructor
  private static class SolutionDuplicateValidator
      implements BusinessRulesValidator<ChefContestantFormData, ChefContestant, String> {

    private final ChefContestService chefContestService;

    @Override
    public void validate(String url,
                         @Nonnull FormContext<ChefContestantFormData, ChefContestant> context)
        throws AttributeBusinessRulesException {

      if (chefContestService.isSolutionExistByUrl(url)) {
        throw ChefContestValidationException.contestantAlreadyExists();
      }
    }
  }
}
