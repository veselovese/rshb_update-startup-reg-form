package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.service.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.AgrotechOlympiadQuestionDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadQuestion;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.service.form.AgrotechOlympiadQuestionForm;

/**
 * Атрибут "Вопрос".
 * Форма подачи вопроса об олимпиаде "Навстречу Агротеху".
 */
@Component("agrotechOlympiadQuestionFormQuestion")
@FormAttribute(AgrotechOlympiadQuestionForm.class)
public class Question extends TextAttributeBase<AgrotechOlympiadQuestionDto, AgrotechOlympiadQuestion> {

  private static final String TITLE_KEY = "agrotech.olympiad.question.form.attribute.question.title";
  private static final int MAX_LENGTH = 1000;


  public Question() {
    super(TITLE_KEY, MAX_LENGTH);
  }

  @Override
  public String supplyInputValue(@Nonnull AgrotechOlympiadQuestionDto inputData) {
    return inputData.getQuestion();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull AgrotechOlympiadQuestion outputData) {
    outputData.setQuestion(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
