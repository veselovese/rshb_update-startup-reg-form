package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.service.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.email.EmailAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.AgrotechOlympiadQuestionDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadQuestion;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.exception.AgrotechOlympiadValidationException;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.service.AgrotechOlympiadService;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.service.form.AgrotechOlympiadQuestionForm;

/**
 * Атрибут "Электронная почта".
 * Форма подачи вопроса об олимпиаде "Навстречу Агротеху".
 */
@Component("agrotechOlympiadQuestionFormEmail")
@FormAttribute(AgrotechOlympiadQuestionForm.class)
public class Email extends EmailAttributeBase<AgrotechOlympiadQuestionDto, AgrotechOlympiadQuestion> {

  private static final int PROCESS_ORDER = 1;

  public Email(@Nonnull AgrotechOlympiadService agrotechOlympiadService) {
    super(new EmailBusinessRulesValidator(agrotechOlympiadService));
  }

  @Override
  public String supplyInputValue(@Nonnull AgrotechOlympiadQuestionDto inputData) {
    return inputData.getEmail();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull AgrotechOlympiadQuestion outputData) {
    outputData.setEmail(outputValue);
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }

  @RequiredArgsConstructor
  private static class EmailBusinessRulesValidator
      implements BusinessRulesValidator<AgrotechOlympiadQuestionDto, AgrotechOlympiadQuestion, String> {

    private final AgrotechOlympiadService agrotechOlympiadService;

    @Override
    public void validate(String email,
                         @Nonnull FormContext<AgrotechOlympiadQuestionDto, AgrotechOlympiadQuestion> context)
        throws AttributeBusinessRulesException {

      if (agrotechOlympiadService.isQuestionsLimitExceeded(email)) {
        throw AgrotechOlympiadValidationException.questionLimitExceeded();
      }
    }
  }
}
