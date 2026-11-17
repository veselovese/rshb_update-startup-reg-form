package ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.censor.forms.CensorAnalyzer;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheck;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckAnalyzer;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckResult;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.exception.RegistrationValidationException;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.InternshipRegistrationForm;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.references.cities.core.repository.CityRepository;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.left;
import static org.apache.commons.lang3.StringUtils.trim;

/**
 * Атрибут "Город".
 * Форма подачи заявки на стажировку.
 */
@Component("internshipRegistrationCity")
@FormAttribute(InternshipRegistrationForm.class)
public class City extends InternshipRegistrationAttribute<City.CityInfo, City.CityInfo> {

  public static final String TITLE_KEY = "internship.registration.form.field.city";


  public City(@Nonnull CityRepository cityRepository,
              @Nonnull CensorAnalyzer censorAnalyzer) {
    super(
        AttributeParams.Builder.<InternshipRegistrationDto, City.CityInfo, Registration, City.CityInfo>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(true)
            .preCheck(new PreCheck<>(new CityPreCheck(censorAnalyzer)))
            .valueMapper(new CityMapper())
            .businessRulesValidator(new CityValidator(cityRepository))
            .build()
    );
  }


  @Override
  public CityInfo supplyInputValue(@Nonnull InternshipRegistrationDto inputData) {
    return new CityInfo(inputData.getCityId(), inputData.getCityCustom());
  }

  @Override
  public void consumeOutputValue(CityInfo outputValue, @Nonnull Registration outputData) {
    outputData.setCityId(outputValue.getId());
    outputData.setCityCustom(outputValue.getName());
  }

  @RequiredArgsConstructor
  private static class CityPreCheck implements PreCheckAnalyzer<CityInfo> {

    private final CensorAnalyzer censorAnalyzer;

    @Nonnull
    @Override
    public PreCheckResult analyze(CityInfo value) {
      return censorAnalyzer.analyze(value.getName());
    }
  }


  @Getter
  @Setter
  @AllArgsConstructor
  public static class CityInfo {
    private String id;
    private String name;
  }

  private static class CityMapper implements ValueMapper<CityInfo, CityInfo> {

    @Override
    public <D, E> CityInfo map(CityInfo value, @Nonnull FormContext<D, E> context) {
      final var id = left(trim(value.getId()), Registration.CITY_MAX_LEN);
      final var name = left(trim(value.getName()), Registration.CITY_MAX_LEN);
      return new CityInfo(id, name);
    }
  }

  @RequiredArgsConstructor
  private static class CityValidator
      implements BusinessRulesValidator<InternshipRegistrationDto, Registration, CityInfo> {

    private final CityRepository cityRepository;

    @Override
    public void validate(CityInfo cityInfo,
                         @Nonnull FormContext<InternshipRegistrationDto, Registration> context)
        throws AttributeBusinessRulesException {

      if (isNotBlank(cityInfo.getName())) {
        cityInfo.setId(null);
        return;
      }

      if (isNotBlank(cityInfo.getId())) {

        if (!cityRepository.existsById(cityInfo.getId())) {
          throw RegistrationValidationException.invalidCityId();
        }

        cityInfo.setName(null);
        return;
      }

      cityInfo.setId(null);
      cityInfo.setName(null);
    }
  }
}
