package ru.rshb.intech.digital.feature.internship.core.service.registration.attribute;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.service.hours.WorkHoursService;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.WorkHours;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тест атрибута \"Количество рабочих часов\" формы заявки на стажировку")
class WorkHoursTest {

  @Mock
  private WorkHoursService workHoursService;

  @InjectMocks
  private WorkHours prefHoursPerWeekAttribute;

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
  void givenValid_process_thenSuccess() {

    given(
        workHoursService.isWorkHoursNotExist("30 часов")
    ).willReturn(false);

    dto.setPrefHrsWk("30 часов");

    prefHoursPerWeekAttribute.process(formContext);
  }

  @Test
  void givenUnknownValue_process_thenException() {

    final var expectedErrorMessage = I18n.of("internship.registration.error.validation.pref-work-hours.incorrect");

    given(
        workHoursService.isWorkHoursNotExist(anyString())
    ).willReturn(true);

    dto.setPrefHrsWk("abra");

    final var exception = assertThrows(
        AttributeBusinessRulesException.class,
        () -> prefHoursPerWeekAttribute.process(formContext)
    );

    assertThat(exception.getMessage())
        .isEqualTo(expectedErrorMessage);
  }

  @Test
  void givenNull_process_thenException() {

    final var expectedErrorMessage = I18n.ofKeys(
        "validation.error.attribute.value.missing",
        WorkHours.TITLE_KEY
    );

    dto.setPrefHrsWk(null);

    final var exception = assertThrows(
        AttributeValueMissingException.class,
        () -> prefHoursPerWeekAttribute.process(formContext)
    );

    assertThat(exception.getMessage())
        .isEqualTo(expectedErrorMessage);
  }

  @Test
  void givenEmpty_process_thenException() {

    final var expectedErrorMessage = I18n.ofKeys(
        "validation.error.attribute.value.missing",
        WorkHours.TITLE_KEY
    );

    dto.setPrefHrsWk(StringUtils.EMPTY);

    final var exception = assertThrows(
        AttributeValueMissingException.class,
        () -> prefHoursPerWeekAttribute.process(formContext)
    );

    assertThat(exception.getMessage())
        .isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Атрибуту назначены свойства -> OK")
  void properties() {
    assertThat(
        prefHoursPerWeekAttribute.properties()
    ).isNotNull();
  }

  @Test
  @DisplayName("Отображаемое имя установлено -> OK")
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        prefHoursPerWeekAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(WorkHours.TITLE_KEY)
    );
  }

  @Test
  @DisplayName("Значение атрибута извлекается из DTO -> OK")
  void supplyInputValue() {

    dto.setPrefHrsWk("20 часов");

    final String result = prefHoursPerWeekAttribute.supplyInputValue(dto);

    assertThat(result)
        .isEqualTo("20 часов");
  }

  @Test
  @DisplayName("Значение атрибута записывается в модель -> OK")
  void consumeOutputValue() {

    final var applicantName = "20 часов";

    prefHoursPerWeekAttribute.consumeOutputValue(applicantName, entity);

    assertThat(
        entity.getWorkHours()
    ).isEqualTo("20 часов");
  }
}
