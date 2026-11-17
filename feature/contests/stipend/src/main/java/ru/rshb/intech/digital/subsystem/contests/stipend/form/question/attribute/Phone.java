package ru.rshb.intech.digital.subsystem.contests.stipend.form.question.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.phone.PhoneAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.subsystem.contests.stipend.dto.StipendContestQuestionFormData;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendContestQuestion;
import ru.rshb.intech.digital.subsystem.contests.stipend.exception.QuestionValidationException;
import ru.rshb.intech.digital.subsystem.contests.stipend.form.question.StipendContestQuestionForm;
import ru.rshb.intech.digital.subsystem.contests.stipend.service.StipendContestQuestionService;

/**
 * Атрибут "Номер телефона".
 * Форма вопроса по стипендиальному конкурсу.
 */
@Component("stipendContestQuestionPhone")
@FormAttribute(StipendContestQuestionForm.class)
public class Phone extends PhoneAttributeBase<StipendContestQuestionFormData, StipendContestQuestion> {

  private static final int PROCESS_ORDER = 1;


  public Phone(@Nonnull StipendContestQuestionService questionService) {
    super(new PhoneDuplicateValidator(questionService));
  }


  @Override
  public String supplyInputValue(@Nonnull StipendContestQuestionFormData inputData) {
    return inputData.getPhone();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull StipendContestQuestion outputData) {
    outputData.setPhone(outputValue);
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }

  @RequiredArgsConstructor
  private static class PhoneDuplicateValidator
      implements BusinessRulesValidator<StipendContestQuestionFormData, StipendContestQuestion, String> {

    private final StipendContestQuestionService questionService;


    @Override
    public void validate(String phone,
                         @Nonnull FormContext<StipendContestQuestionFormData, StipendContestQuestion> context)
        throws AttributeBusinessRulesException {

      if (questionService.isMaxQuestionsPerUserExceededByPhone(phone)) {
        throw QuestionValidationException.maxQuestionsPerUserExceeded();
      }
    }
  }
}
