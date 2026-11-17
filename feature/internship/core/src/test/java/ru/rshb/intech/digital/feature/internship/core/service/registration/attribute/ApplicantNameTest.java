package ru.rshb.intech.digital.feature.internship.core.service.registration.attribute;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.attributes.applicant.ApplicantNameAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.ApplicantName;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тест атрибута \"ФИО\" формы заявки на стажировку")
class ApplicantNameTest {

  @InjectMocks
  private ApplicantName applicantNameAttribute;

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
  @DisplayName("Указаны все элементы ФИО -> OK")
  void givenFio_process_thenSuccess() {

    dto.setApplicantName("Кропоткин Петр Алексеевич");

    applicantNameAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getApplicantName()
    ).isEqualTo("Кропоткин Петр Алексеевич");
  }

  @Test
  @DisplayName("Указано составное отчество -> OK")
  void givenCompositeFio_process_thenSuccess() {

    dto.setApplicantName("Аббасов Фахраддин Фарман оглы");

    applicantNameAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getApplicantName()
    ).isEqualTo("Аббасов Фахраддин Фарман оглы");
  }

  @Test
  @DisplayName("Указана составная фамилия -> OK")
  void givenCompositeSurname_process_thenSuccess() {

    dto.setApplicantName("Муравьев-Апостол Сергей Иванович");

    applicantNameAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getApplicantName()
    ).isEqualTo("Муравьев-Апостол Сергей Иванович");
  }

  @Test
  @DisplayName("Указана фамилия и имя -> OK")
  void givenSurnameAndName_process_thenSuccess() {

    dto.setApplicantName("Юлаев Салават");

    applicantNameAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getApplicantName()
    ).isEqualTo("Юлаев Салават");
  }

  @Test
  @DisplayName("Значение атрибута не задано -> Exception")
  void givenNoFio_process_thenException() {

    final var expectedErrorMessage = I18n.of(
        "validation.error.attribute.value.missing",
        applicantNameAttribute.properties().getName()
    );

    dto.setApplicantName(null);

    final var exception = assertThrowsExactly(AttributeValueMissingException.class, () -> {
      applicantNameAttribute.process(formContext);
    });

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Пустая строка -> Exception")
  void givenBlankFio_process_thenException() {

    final var expectedErrorMessage = I18n.of(
        "validation.error.attribute.value.missing",
        applicantNameAttribute.properties().getName()
    );

    dto.setApplicantName(StringUtils.EMPTY);

    final var exception = assertThrowsExactly(AttributeValueMissingException.class, () -> {
      applicantNameAttribute.process(formContext);
    });

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Атрибуту назначены свойства -> OK")
  void properties() {
    assertThat(
        applicantNameAttribute.properties()
    ).isNotNull();
  }

  @Test
  @DisplayName("Отображаемое имя установлено -> OK")
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        applicantNameAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(ApplicantNameAttributeBase.DEFAULT_TITLE_KEY)
    );
  }

  @Test
  @DisplayName("Значение атрибута извлекается из DTO -> OK")
  void supplyInputValue() {

    dto.setApplicantName("Иванов Иван Иванович");

    final String result = applicantNameAttribute.supplyInputValue(dto);

    assertThat(result)
        .isEqualTo("Иванов Иван Иванович");
  }

  @Test
  @DisplayName("Значение атрибута записывается в модель -> OK")
  void consumeOutputValue() {

    final var applicantName = "Иванов Иван Иванович";

    applicantNameAttribute.consumeOutputValue(applicantName, entity);

    assertThat(
        entity.getApplicantName()
    ).isEqualTo("Иванов Иван Иванович");
  }
}