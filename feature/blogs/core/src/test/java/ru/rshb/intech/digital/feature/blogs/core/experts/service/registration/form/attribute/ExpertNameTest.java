package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.attributes.applicant.ApplicantNameAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationFormData;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(MockitoExtension.class)
class ExpertNameTest {

  @InjectMocks
  private ExpertName expertNameAttribute;

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
  void givenFio_process_thenSuccess() {

    dto.setExpertName("Кропоткин Петр Алексеевич");

    expertNameAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getExpertName()
    ).isEqualTo("Кропоткин Петр Алексеевич");
  }

  @Test
  void givenCompositeFio_process_thenSuccess() {

    dto.setExpertName("Аббасов Фахраддин Фарман оглы");

    expertNameAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getExpertName()
    ).isEqualTo("Аббасов Фахраддин Фарман оглы");
  }

  @Test
  void givenCompositeSurname_process_thenSuccess() {

    dto.setExpertName("Муравьев-Апостол Сергей Иванович");

    expertNameAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getExpertName()
    ).isEqualTo("Муравьев-Апостол Сергей Иванович");
  }

  @Test
  void givenSurnameAndName_process_thenSuccess() {

    dto.setExpertName("Юлаев Салават");

    expertNameAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getExpertName()
    ).isEqualTo("Юлаев Салават");
  }

  @Test
  void givenNoFio_process_thenException() {

    final var expectedErrorMessage = I18n.of(
        "validation.error.attribute.value.missing",
        expertNameAttribute.properties().getName()
    );

    dto.setExpertName(null);

    final var exception = assertThrowsExactly(AttributeValueMissingException.class, () -> {
      expertNameAttribute.process(formContext);
    });

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  void givenBlankFio_process_thenException() {

    final var expectedErrorMessage = I18n.of(
        "validation.error.attribute.value.missing",
        expertNameAttribute.properties().getName()
    );

    dto.setExpertName(StringUtils.EMPTY);

    final var exception = assertThrowsExactly(AttributeValueMissingException.class, () -> {
      expertNameAttribute.process(formContext);
    });

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  void properties() {
    assertThat(
        expertNameAttribute.properties()
    ).isNotNull();
  }

  @Test
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        expertNameAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(ApplicantNameAttributeBase.DEFAULT_TITLE_KEY)
    );
  }

  @Test
  void supplyInputValue() {

    dto.setExpertName("Иванов Иван Иванович");

    final String result = expertNameAttribute.supplyInputValue(dto);

    assertThat(result)
        .isEqualTo("Иванов Иван Иванович");
  }

  @Test
  void consumeOutputValue() {

    final var applicantName = "Иванов Иван Иванович";

    expertNameAttribute.consumeOutputValue(applicantName, entity);

    assertThat(
        entity.getExpertName()
    ).isEqualTo("Иванов Иван Иванович");
  }
}
