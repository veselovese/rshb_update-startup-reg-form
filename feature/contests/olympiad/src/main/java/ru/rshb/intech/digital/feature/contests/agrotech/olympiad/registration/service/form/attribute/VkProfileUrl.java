package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.attribute;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.vk.VkUrlAttributeBase;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.AgrotechOlympiadRegistrationDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadRegistration;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.AgrotechOlympiadRegistrationForm;

/**
 * Атрибут "Ссылка на профиль VK".
 * Форма заявки на участие в олимпиаде "Навстречу Агротеху".
 */
@Component("agrotechOlympiadRegistrationVkProfileUrl")
@FormAttribute(AgrotechOlympiadRegistrationForm.class)
public class VkProfileUrl
    extends VkUrlAttributeBase<AgrotechOlympiadRegistrationDto, AgrotechOlympiadRegistration> {


  @Override
  public String supplyInputValue(AgrotechOlympiadRegistrationDto inputData) {
    return inputData.vkProfileUrl();
  }

  @Override
  public void consumeOutputValue(String outputValue, AgrotechOlympiadRegistration outputData) {
    outputData.setVkProfileUrl(outputValue);
  }
}
