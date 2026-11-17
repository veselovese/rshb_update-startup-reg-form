package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.attributes.phone.PhoneAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationFormData;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(MockitoExtension.class)
class PhoneTest {

  @InjectMocks
  private Phone phoneAttribute;

  private ExpertRegistrationFormData dto;
  private ExpertRegistration entity;
  private FormContext<ExpertRegistrationFormData, ExpertRegistration> formContext;

  @BeforeEach
  void setUp() {
    dto = new ExpertRegistrationFormData();
    entity = new ExpertRegistration();
    formContext = new FormContext<>("expertRegistration", dto, entity);
  }

  @Test
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
  void properties() {
    assertThat(
        phoneAttribute.properties()
    ).isNotNull();
  }

  @Test
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        phoneAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(PhoneAttributeBase.DEFAULT_TITLE_KEY)
    );
  }

  @Test
  void supplyInputValue() {

    final var testData = "+7 (999) 666-42-42";

    dto.setPhone(testData);

    final String result = phoneAttribute.supplyInputValue(dto);

    assertThat(result)
        .isEqualTo(testData);
  }

  @Test
  void consumeOutputValue() {

    final var testData = "+79996664242";

    phoneAttribute.consumeOutputValue(testData, entity);

    assertThat(
        entity.getPhone()
    ).isEqualTo(testData);
  }
}