package ru.rshb.intech.digital.subsystem.contests.stipend.form.solution.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.applicant.ApplicantNameAttributeBase;
import ru.rshb.intech.digital.subsystem.contests.stipend.dto.StipendContestSolutionFormData;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendContestSolution;
import ru.rshb.intech.digital.subsystem.contests.stipend.form.solution.StipendContestTaskSolutionForm;

/**
 * Атрибут "ФИО".
 * Форма решения по стипендиальному конкурсу.
 */
@Component("stipendContestSolutionContestantName")
@FormAttribute(StipendContestTaskSolutionForm.class)
public class ContestantName
    extends ApplicantNameAttributeBase<StipendContestSolutionFormData, StipendContestSolution> {

  @Override
  public String supplyInputValue(@Nonnull StipendContestSolutionFormData inputData) {
    return inputData.getContestantName();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull StipendContestSolution outputData) {
    outputData.setParticipantName(outputValue);
  }
}
