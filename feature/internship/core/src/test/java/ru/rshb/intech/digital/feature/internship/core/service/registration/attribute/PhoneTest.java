package ru.rshb.intech.digital.feature.internship.core.service.registration.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.attributes.phone.PhoneAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.repository.registration.RegistrationRepository;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.Phone;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тест атрибута \"Номер телефона\" формы заявки на стажировку")
class PhoneTest {

  @Mock
  private RegistrationRepository registrationRepository;

  @InjectMocks
  private Phone phoneAttribute;

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
  @DisplayName("Отформатированный номер телефона, отсутствующий в заявках -> OK")
  void givenPhoneNonPresentedInRegistrations_process_thenSuccess() {

    given(
        registrationRepository.existsRegistrationByPhone(any())
    ).willReturn(false);

    dto.setPhone("+7 (999) 666-42-42");

    phoneAttribute.process(formContext);
  }

  @Test
  @DisplayName("Телефон присутствует в заявках -> Exception")
  void givenPhoneAlreadyPresentedInRegistration_process_thenException() {

    final var expectedErrorMessage = I18n.of("common.error.validation.phone.already.exist");

    given(
        registrationRepository.existsRegistrationByPhone(any())
    ).willReturn(true);

    dto.setPhone("+7 (999) 666-42-42");

    final var exception = assertThrowsExactly(
        AttributeBusinessRulesException.class,
        () -> phoneAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Не телефон -> Exception")
  void givenPhoneBadFormat_process_thenException() {

    final var expectedErrorMessage = I18n.ofKeys(
        "validation.error.attribute.data.format",
        Phone.DEFAULT_TITLE_KEY
    );

    dto.setPhone("abracadabra");

    final var exception = assertThrowsExactly(
        AttributeBadFormatException.class,
        () -> phoneAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Свойства атриубта есть -> OK")
  void properties() {
    assertThat(phoneAttribute.properties()).isNotNull();
  }

  @Test
  @DisplayName("Отображаемое имя задано -> OK")
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        phoneAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(PhoneAttributeBase.DEFAULT_TITLE_KEY)
    );
  }

  @Test
  @DisplayName("Значение атрибута извлекается из DTO -> OK")
  void supplyInputValue() {

    dto.setPhone("+7 (999) 666-42-42");

    final String result = phoneAttribute.supplyInputValue(dto);

    assertThat(result)
        .isEqualTo("+7 (999) 666-42-42");
  }

  @Test
  @DisplayName("Значение атрибута записывается в модель -> OK")
  void consumeOutputValue() {

    final var phone = "+79996664242";

    phoneAttribute.consumeOutputValue(phone, entity);

    assertThat(entity.getPhone())
        .isEqualTo(phone);
  }
}
