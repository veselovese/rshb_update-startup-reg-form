package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.attribute;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.AgrotechOlympiadRegistrationDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadRegistration;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.exception.AgrotechOlympiadValidationException;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.AgrotechOlympiadRegistrationLookupService;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.AgrotechOlympiadRegistrationForm;

/**
 * Атрибут ID ВУЗ'а.
 * Форма заявки на участие в олимпиаде "Навстречу Агротеху".
 */
@Component("agrotechOlympiadRegistrationUniversity")
@FormAttribute(AgrotechOlympiadRegistrationForm.class)
public class UniversityId extends TextAttributeBase<AgrotechOlympiadRegistrationDto, AgrotechOlympiadRegistration> {

  private static final String TITLE_KEY = "agrotech.olympiad.registration.form.attribute.university.title";
  private static final int MAX_LENGTH = 100;

  private final AgrotechOlympiadRegistrationLookupService lookupService;


  public UniversityId(AgrotechOlympiadRegistrationLookupService lookupService) {
    super(TITLE_KEY, false, MAX_LENGTH);
    this.lookupService = lookupService;
  }

  @Override
  public String supplyInputValue(AgrotechOlympiadRegistrationDto inputData) {
    return inputData.universityId();
  }

  @Override
  public void consumeOutputValue(String universityId, AgrotechOlympiadRegistration outputData) {

    final var university = lookupService.findUniversityByExternalId(universityId)
            .orElseThrow(() -> AgrotechOlympiadValidationException.universityNotFound(universityId));

    outputData.setUniversity(university);
  }
}
