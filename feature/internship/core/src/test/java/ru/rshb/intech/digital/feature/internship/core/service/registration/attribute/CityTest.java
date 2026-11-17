package ru.rshb.intech.digital.feature.internship.core.service.registration.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.censor.forms.CensorAnalyzer;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.City;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.references.cities.core.repository.CityRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тест атрибута \"Город\" формы подачи заявки на стажировку")
class CityTest {

  @Mock
  private CityRepository cityRepository;

  @Mock
  private CensorAnalyzer censorAnalyzer;

  @InjectMocks
  private City cityAttribute;

  private InternshipRegistrationDto dto;
  private Registration entity;
  private FormContext<InternshipRegistrationDto, Registration> formContext;

  @BeforeEach
  void setUp() {
    dto = new InternshipRegistrationDto();
    entity = new Registration();
    formContext = new FormContext<>("registration", dto, entity);
  }

  @Test
  @DisplayName("ID существующего города -> OK")
  void givenCityIdWithoutCityName_process_thenSuccess() {

    final var cityFromDb = new ru.rshb.intech.digital.references.cities.entity.City();
    cityFromDb.setId("45.000.000.000.1");
    cityFromDb.setTitle("Москва");
    cityFromDb.setRegion("Город федерального значения");

    given(cityRepository.existsById(cityFromDb.getId()))
        .willReturn(true);

    dto.setCityId("45.000.000.000.1");

    cityAttribute.process(formContext);
  }

  @Test
  @DisplayName("Город не из справочника -> OK")
  void givenCustomCityName_process_thenSuccess() {

    dto.setCityCustom("Квадрицепстаун");

    cityAttribute.process(formContext);
  }

  @Test
  @DisplayName("ID города, отсутствующий в справочнике -> Exception")
  void givenUnknownCityId_process_thenException() {

    final var expectedErrorMessage = I18n.of("internship.registration.error.validation.city.id.format");

    dto.setCityId("123");

    final var exception = assertThrowsExactly(AttributeBusinessRulesException.class, () -> {
      cityAttribute.process(formContext);
    });

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Нет ни ID города, ни названия -> OK")
  void givenNoCityIdAndNoCustomCityName_process_thenSuccess() {

    dto.setCityId(null);
    dto.setCityCustom(null);

    cityAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getCityId()
    ).isNull();

    assertThat(
        formContext.getOutputData().getCityCustom()
    ).isNull();
  }

  @Test
  @DisplayName("Свойства атриубта есть -> OK")
  void properties() {
    assertThat(
        cityAttribute.properties()
    ).isNotNull();
  }

  @Test
  @DisplayName("Отображаемое имя задано -> OK")
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        cityAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(City.TITLE_KEY)
    );
  }

  @Test
  @DisplayName("Значение атрибута извлекается из DTO -> OK")
  void supplyInputValue() {

    dto.setCityId("123");
    dto.setCityCustom("Квадрицепстаун");

    final City.CityInfo result = cityAttribute.supplyInputValue(dto);

    assertThat(result.getId())
        .isEqualTo("123");

    assertThat(result.getName())
        .isEqualTo("Квадрицепстаун");
  }

  @Test
  @DisplayName("Значение атрибута записывается в модель -> OK")
  void consumeOutputValue() {

    final var cityInfo = new City.CityInfo("123", "abracadabra");

    cityAttribute.consumeOutputValue(cityInfo, entity);

    assertThat(entity.getCityId())
        .isEqualTo("123");

    assertThat(entity.getCityCustom())
        .isEqualTo("abracadabra");
  }
}