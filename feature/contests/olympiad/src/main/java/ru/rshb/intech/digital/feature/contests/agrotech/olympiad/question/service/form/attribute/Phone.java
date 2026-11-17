package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.service.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.phone.PhoneAttributeBase;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.AgrotechOlympiadQuestionDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadQuestion;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.service.form.AgrotechOlympiadQuestionForm;

/**
 * Атрибут "Номер телефона".
 * Форма подачи вопроса об олимпиаде "Навстречу Агротеху".
 */
@Component("agrotechOlympiadQuestionFormPhone")
@FormAttribute(AgrotechOlympiadQuestionForm.class)
public class Phone extends PhoneAttributeBase<AgrotechOlympiadQuestionDto, AgrotechOlympiadQuestion> {

  @Override
  public String supplyInputValue(@Nonnull AgrotechOlympiadQuestionDto inputData) {
    return inputData.getPhone();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull AgrotechOlympiadQuestion outputData) {
    outputData.setPhone(outputValue);
  }
}
