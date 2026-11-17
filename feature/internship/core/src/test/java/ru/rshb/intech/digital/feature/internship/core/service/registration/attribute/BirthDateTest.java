package ru.rshb.intech.digital.feature.internship.core.service.registration.attribute;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.BirthDate;
import ru.rshb.intech.digital.messages.I18n;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тест атрибута \"Дата рождения\" формы заявки на стажировку")
class BirthDateTest {

  @InjectMocks
  private BirthDate birthDateAttribute;

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
  @DisplayName("Дата в формате ДД.ММ.ГГГ -> OK")
  void givenValidDate_process_thenSuccess() {

    dto.setBirthDate("01.12.1995");

    birthDateAttribute.process(formContext);
  }

  @Test
  @DisplayName("Значение атрибута не задано -> Exception")
  void givenNull_process_thenException() {

    final var expectedErrorMessage = I18n.of(
        "validation.error.attribute.value.missing",
        birthDateAttribute.properties().getName()
    );

    dto.setBirthDate(null);

    final var exception = assertThrowsExactly(AttributeValueMissingException.class, () -> {
      birthDateAttribute.process(formContext);
    });

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Пустая строка -> Exception")
  void givenBlank_process_thenException() {

    final var expectedErrorMessage = I18n.of(
        "validation.error.attribute.value.missing",
        birthDateAttribute.properties().getName()
    );

    dto.setBirthDate(StringUtils.EMPTY);

    final var exception = assertThrowsExactly(AttributeValueMissingException.class, () -> {
      birthDateAttribute.process(formContext);
    });

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Некорректный формат даты -> Exception")
  void givenBadFormat_process_thenException() {

    final var expectedErrorMessage = I18n.ofKeys(
        "validation.error.attribute.data.format",
        BirthDate.TITLE_KEY
    );

    dto.setBirthDate("abracadabra");

    final var context = new FormContext<>("registration", dto, entity);

    final var exception = assertThrowsExactly(AttributeBadFormatException.class, () -> {
      birthDateAttribute.process(context);
    });

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Дата из будущего -> Exception")
  void givenFutureDate_process_thenException() {

    final var expectedErrorMessage = I18n.of("internship.registration.error.validation.birthdate.value");

    dto.setBirthDate("23.06.2189");

    final var exception = assertThrowsExactly(AttributeBusinessRulesException.class, () -> {
      birthDateAttribute.process(formContext);
    });

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Текущая дата -> Exception")
  void givenCurrentDate_process_thenException() {

    final var expectedErrorMessage = I18n.of("internship.registration.error.validation.birthdate.value");
    final var today = SIMPLE_DATE.format(LocalDate.now());

    dto.setBirthDate(today);

    final var exception = assertThrowsExactly(AttributeBusinessRulesException.class, () -> {
      birthDateAttribute.process(formContext);
    });

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Атрибуту назначены свойства -> OK")
  void properties() {
    assertThat(
        birthDateAttribute.properties()
    ).isNotNull();
  }

  @Test
  @DisplayName("Отображаемое имя установлено -> OK")
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        birthDateAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(BirthDate.TITLE_KEY)
    );
  }

  @Test
  @DisplayName("Значение атрибута извлекается из DTO -> OK")
  void supplyInputValue() {

    dto.setBirthDate("13.12.2002");

    final String result = birthDateAttribute.supplyInputValue(dto);

    assertThat(result).isEqualTo("13.12.2002");
  }

  @Test
  @DisplayName("Значение атрибута записывается в модель -> OK")
  void consumeOutputValue() {

    final var localDate = LocalDate.of(2002, 12, 13);

    birthDateAttribute.consumeOutputValue(localDate, entity);

    assertThat(
        entity.getBirthDate()
    ).isEqualTo(localDate);
  }
}