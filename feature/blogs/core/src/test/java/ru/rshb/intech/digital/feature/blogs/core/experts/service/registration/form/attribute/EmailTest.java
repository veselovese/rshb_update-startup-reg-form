package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.attributes.email.EmailAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationFormData;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(MockitoExtension.class)
class EmailTest {

  @InjectMocks
  private Email emailAttribute;

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
  void givenValidAddress_process_thenSuccess() {

    final var testData = "test@test.te";

    dto.setEmail(testData);

    emailAttribute.process(formContext);
  }

  @Test
  void givenInvalidAddressFormat_process_thenException() {

    final var testData = "abracadabra";

    dto.setEmail(testData);

    final var exception = assertThrowsExactly(
        AttributeBadFormatException.class,
        () -> emailAttribute.process(formContext)
    );

    final var expectedErrorMessage = I18n.ofKeys(
        "validation.error.attribute.data.format",
        Email.DEFAULT_TITLE_KEY
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  void properties() {
    assertThat(
        emailAttribute.properties()
    ).isNotNull();
  }

  @Test
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        emailAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(EmailAttributeBase.DEFAULT_TITLE_KEY)
    );
  }

  @Test
  void supplyInputValue() {

    final var testData = "test@test.te";

    dto.setEmail(testData);

    final String result = emailAttribute.supplyInputValue(dto);

    assertThat(result)
        .isEqualTo(testData);
  }

  @Test
  void consumeOutputValue() {

    final var testData = "test@test.te";

    emailAttribute.consumeOutputValue(testData, entity);

    assertThat(
        entity.getEmail()
    ).isEqualTo(testData);
  }
}