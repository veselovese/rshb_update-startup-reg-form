package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.attribute;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.phone.PhoneAttributeBase;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.AgrotechOlympiadRegistrationDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadRegistration;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.AgrotechOlympiadRegistrationForm;

/**
 * Атрибут "Номер телефона".
 * Форма заявки на участие в олимпиаде "Навстречу Агротеху".
 */
@Component("agrotechOlympiadRegistrationPhone")
@FormAttribute(AgrotechOlympiadRegistrationForm.class)
public class Phone extends PhoneAttributeBase<AgrotechOlympiadRegistrationDto, AgrotechOlympiadRegistration> {

  @Override
  public String supplyInputValue(AgrotechOlympiadRegistrationDto inputData) {
    return inputData.phone();
  }

  @Override
  public void consumeOutputValue(String outputValue, AgrotechOlympiadRegistration outputData) {
    outputData.setPhone(outputValue);
  }
}
