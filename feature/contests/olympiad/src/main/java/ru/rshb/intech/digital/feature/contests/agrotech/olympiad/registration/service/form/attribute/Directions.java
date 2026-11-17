package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextListAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.AgrotechOlympiadRegistrationDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadRegistration;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.AgrotechOlympiadRegistrationLookupService;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.AgrotechOlympiadRegistrationForm;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Атрибут "ВУЗ".
 * Форма заявки на участие в олимпиаде "Навстречу Агротеху".
 */
@Component("agrotechOlympiadRegistrationDirections")
@FormAttribute(AgrotechOlympiadRegistrationForm.class)
public class Directions
    extends TextListAttributeBase<AgrotechOlympiadRegistrationDto, AgrotechOlympiadRegistration, String> {

  private static final String TITLE_KEY = "agrotech.olympiad.registration.form.attribute.university.title";
  private static final int MAX_DIRECTIONS_NUM = 4;
  private static final int MAX_DIRECTION_LENGTH = 100;

  private final AgrotechOlympiadRegistrationLookupService lookupService;

  public Directions(AgrotechOlympiadRegistrationLookupService lookupService) {
    super(TITLE_KEY, MAX_DIRECTIONS_NUM, MAX_DIRECTION_LENGTH);

    this.lookupService = lookupService;
  }

  @Override
  public List<String> supplyInputValue(AgrotechOlympiadRegistrationDto inputData) {
    return inputData.directionIds();
  }

  @Override
  public void consumeOutputValue(String outputValue, AgrotechOlympiadRegistration outputData) {
    outputData.setDirections(outputValue);
  }

  @Override
  public <D, E> String map(List<String> rawValue, @Nonnull FormContext<D, E> context) {
    return rawValue.stream()
        .map(id -> lookupService.findDirection(id)
            .orElseThrow(() -> new AttributeBadFormatException(properties().getName()))
        ).collect(Collectors.joining(","));
  }
}
