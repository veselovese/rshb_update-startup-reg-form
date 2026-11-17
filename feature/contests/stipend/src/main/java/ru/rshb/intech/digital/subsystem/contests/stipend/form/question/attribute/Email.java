package ru.rshb.intech.digital.subsystem.contests.stipend.form.question.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.email.EmailAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.subsystem.contests.stipend.dto.StipendContestQuestionFormData;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendContestQuestion;
import ru.rshb.intech.digital.subsystem.contests.stipend.exception.QuestionValidationException;
import ru.rshb.intech.digital.subsystem.contests.stipend.form.question.StipendContestQuestionForm;
import ru.rshb.intech.digital.subsystem.contests.stipend.service.StipendContestQuestionService;

/**
 * Атрибут "Электронная почта".
 * Форма вопроса по стипендиальному конкурсу.
 */
@Component("stipendContestQuestionEmail")
@FormAttribute(StipendContestQuestionForm.class)
public class Email extends EmailAttributeBase<StipendContestQuestionFormData, StipendContestQuestion> {

  private static final int PROCESS_ORDER = 2;


  public Email(@Nonnull StipendContestQuestionService questionService) {
    super(new EmailDuplicateValidator(questionService));
  }


  @Override
  public String supplyInputValue(@Nonnull StipendContestQuestionFormData inputData) {
    return inputData.getEmail();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull StipendContestQuestion outputData) {
    outputData.setEmail(outputValue);
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }

  @RequiredArgsConstructor
  private static class EmailDuplicateValidator
      implements BusinessRulesValidator<StipendContestQuestionFormData, StipendContestQuestion, String> {

    private final StipendContestQuestionService questionService;

    @Override
    public void validate(String email,
                         @Nonnull FormContext<StipendContestQuestionFormData, StipendContestQuestion> context)
        throws AttributeBusinessRulesException {

      if (questionService.isMaxQuestionsPerUserExceededByEmail(email)) {
        throw QuestionValidationException.maxQuestionsPerUserExceeded();
      }
    }
  }
}
