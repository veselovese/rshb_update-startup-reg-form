package ru.rshb.intech.digital.feature.internship.core.service.registration.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.attributes.email.EmailAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.Email;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тест атрибута \"Email\" формы заявки на стажировку")
class EmailTest {

  @InjectMocks
  private Email emailAttribute;

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
  @DisplayName("Email адрес -> OK")
  void givenValidAddress_process_thenSuccess() {

    dto.setEmail("test@test.te");

    emailAttribute.process(formContext);
  }

  @Test
  @DisplayName("Не Email -> Exception")
  void givenInvalidAddressFormat_process_thenException() {

    final var expectedErrorMessage = I18n.ofKeys(
        "validation.error.attribute.data.format",
        Email.DEFAULT_TITLE_KEY
    );

    dto.setEmail("abracadabra");

    final var exception = assertThrowsExactly(
        AttributeBadFormatException.class,
        () -> emailAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Свойства атриубта есть -> OK")
  void properties() {
    assertThat(
        emailAttribute.properties()
    ).isNotNull();
  }

  @Test
  @DisplayName("Отображаемое имя задано -> OK")
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        emailAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(EmailAttributeBase.DEFAULT_TITLE_KEY)
    );
  }

  @Test
  @DisplayName("Значение атрибута извлекается из DTO -> OK")
  void supplyInputValue() {

    dto.setEmail("test@test.te");

    final String result = emailAttribute.supplyInputValue(dto);

    assertThat(result)
        .isEqualTo("test@test.te");
  }

  @Test
  @DisplayName("Значение атрибута записывается в модель -> OK")
  void consumeOutputValue() {

    final var email = "test@test.te";

    emailAttribute.consumeOutputValue(email, entity);

    assertThat(entity.getEmail())
        .isEqualTo("test@test.te");
  }
}