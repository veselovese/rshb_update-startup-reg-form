package ru.rshb.intech.digital.feature.contests.chef.service.contestant.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.applicant.ApplicantNameAttributeBase;
import ru.rshb.intech.digital.feature.contests.chef.dto.ChefContestantFormData;
import ru.rshb.intech.digital.feature.contests.chef.entity.ChefContestant;
import ru.rshb.intech.digital.feature.contests.chef.service.contestant.form.ChefContestantForm;

/**
 * Атрибут "Имя участника".
 * Форма участника конкурса "Цифровой повар".
 */
@Component("chefContestantFormContestantName")
@FormAttribute(ChefContestantForm.class)
public class ContestantName extends ApplicantNameAttributeBase<ChefContestantFormData, ChefContestant> {

  private static final String TITLE_KEY = "contest.chef.participant.form.attribute.name";

  public ContestantName() {
    super(TITLE_KEY, false, ChefContestant.NAME_LENGTH);
  }


  @Override
  public String supplyInputValue(@Nonnull ChefContestantFormData inputData) {
    return inputData.getContestantName();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull ChefContestant outputData) {
    outputData.setName(outputValue);
  }
}
