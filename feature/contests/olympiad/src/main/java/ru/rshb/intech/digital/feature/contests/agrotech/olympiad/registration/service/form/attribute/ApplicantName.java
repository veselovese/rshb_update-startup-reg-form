package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.attribute;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.applicant.ApplicantNameAttributeBase;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.AgrotechOlympiadRegistrationDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadRegistration;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.AgrotechOlympiadRegistrationForm;

/**
 * Атрибут "ФИО".
 * Форма заявки на участие в олимпиаде "Навстречу Агротеху".
 */
@Component("agrotechOlympiadRegistrationApplicantName")
@FormAttribute(AgrotechOlympiadRegistrationForm.class)
public class ApplicantName
    extends ApplicantNameAttributeBase<AgrotechOlympiadRegistrationDto, AgrotechOlympiadRegistration> {

  @Override
  public String supplyInputValue(AgrotechOlympiadRegistrationDto inputData) {
    return inputData.applicantName();
  }

  @Override
  public void consumeOutputValue(String outputValue, AgrotechOlympiadRegistration outputData) {
    outputData.setApplicantName(outputValue);
  }
}
