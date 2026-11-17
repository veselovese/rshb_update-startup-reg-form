package ru.rshb.intech.digital.subsystem.contests.stipend.form.question.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.subsystem.contests.stipend.dto.StipendContestQuestionFormData;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendContestQuestion;
import ru.rshb.intech.digital.subsystem.contests.stipend.form.question.StipendContestQuestionForm;

/**
 * Атрибут "Вопрос".
 * Форма вопроса по стипендиальному конкурсу.
 */
@Component("stipendContestQuestion")
@FormAttribute(StipendContestQuestionForm.class)
public class Question extends TextAttributeBase<StipendContestQuestionFormData, StipendContestQuestion> {

  private static final String TITLE_KEY = "contest.stipend.question.form.attribute.question";


  public Question() {
    super(TITLE_KEY, StipendContestQuestion.QUESTION_LENGTH);
  }

  @Override
  public String supplyInputValue(@Nonnull StipendContestQuestionFormData inputData) {
    return inputData.getQuestion();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull StipendContestQuestion outputData) {
    outputData.setQuestion(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
