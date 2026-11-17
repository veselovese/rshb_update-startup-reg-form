package ru.rshb.intech.digital.feature.patents.core.service.publication.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.feature.patents.core.dto.PatentPublicationDto;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.exception.PublicationValidationException;
import ru.rshb.intech.digital.feature.patents.core.service.publication.form.PatentPublicationForm;
import ru.rshb.intech.digital.references.cities.core.service.CityReferenceService;

/**
 * Атрибут "Город".
 * Форма заявки на размещение патента в витрине патентов.
 */
@Component("patentPubFormCity")
@FormAttribute(PatentPublicationForm.class)
public class City extends TextAttributeBase<PatentPublicationDto, PatentPublication> {

  public static final String TITLE_KEY = "common.form.attribute.city.title";
  private static final int MAX_LENGTH = 100;

  public City(@Nonnull CityReferenceService cityReferenceService) {
    super(TITLE_KEY, MAX_LENGTH, new CityValueMapper(cityReferenceService));
  }

  @Override
  public String supplyInputValue(@Nonnull PatentPublicationDto inputData) {
    return inputData.getCityId();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentPublication outputData) {
    outputData.setCityId(outputValue);
  }

  @RequiredArgsConstructor
  private static class CityValueMapper implements ValueMapper<String, String> {

    private final CityReferenceService cityReferenceService;

    @Override
    public <D, E> String map(String cityId, @Nonnull FormContext<D, E> context) {
      if (cityReferenceService.isCityExistById(cityId)) {
        return cityId;
      }
      throw PublicationValidationException.unknownCityId();
    }
  }
}
