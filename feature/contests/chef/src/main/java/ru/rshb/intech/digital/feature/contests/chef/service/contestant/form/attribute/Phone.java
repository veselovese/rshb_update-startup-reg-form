package ru.rshb.intech.digital.feature.contests.chef.service.contestant.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.phone.PhoneAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.contests.chef.dto.ChefContestantFormData;
import ru.rshb.intech.digital.feature.contests.chef.entity.ChefContestant;
import ru.rshb.intech.digital.feature.contests.chef.exception.ChefContestValidationException;
import ru.rshb.intech.digital.feature.contests.chef.service.ChefContestService;
import ru.rshb.intech.digital.feature.contests.chef.service.contestant.form.ChefContestantForm;

/**
 * Атрибут "Номер телефона".
 * Форма участника конкурса "Цифровой повар".
 */
@Component("chefContestFormPhone")
@FormAttribute(ChefContestantForm.class)
public class Phone extends PhoneAttributeBase<ChefContestantFormData, ChefContestant> {


  public Phone(@Nonnull ChefContestService chefContestService) {
    super(new DuplicateValidator(chefContestService));
  }


  @Override
  public String supplyInputValue(@Nonnull ChefContestantFormData inputData) {
    return inputData.getPhone();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull ChefContestant outputData) {
    outputData.setPhone(outputValue);
  }


  @RequiredArgsConstructor
  private static class DuplicateValidator
      implements BusinessRulesValidator<ChefContestantFormData, ChefContestant, String> {

    private final ChefContestService chefContestService;

    @Override
    public void validate(String phone,
                         @Nonnull FormContext<ChefContestantFormData, ChefContestant> context)
        throws AttributeBusinessRulesException {

      if (chefContestService.isSolutionExistByPhone(phone)) {
        throw ChefContestValidationException.contestantAlreadyExists();
      }
    }
  }
}
