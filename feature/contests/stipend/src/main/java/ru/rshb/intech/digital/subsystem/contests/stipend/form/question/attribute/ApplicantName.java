package ru.rshb.intech.digital.subsystem.contests.stipend.form.question.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.applicant.ApplicantNameAttributeBase;
import ru.rshb.intech.digital.subsystem.contests.stipend.dto.StipendContestQuestionFormData;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendContestQuestion;
import ru.rshb.intech.digital.subsystem.contests.stipend.form.question.StipendContestQuestionForm;

/**
 * Атрибут "ФИО".
 * Форма вопроса по стипендиальному конкурсу.
 */
@Component("stipendContestQuestionApplicant")
@FormAttribute(StipendContestQuestionForm.class)
public class ApplicantName
    extends ApplicantNameAttributeBase<StipendContestQuestionFormData, StipendContestQuestion> {

  @Override
  public String supplyInputValue(@Nonnull StipendContestQuestionFormData inputData) {
    return inputData.getApplicantName();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull StipendContestQuestion outputData) {
    outputData.setApplicantName(outputValue);
  }
}
