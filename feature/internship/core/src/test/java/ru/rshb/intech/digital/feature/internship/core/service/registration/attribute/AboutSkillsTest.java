package ru.rshb.intech.digital.feature.internship.core.service.registration.attribute;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.AboutSkills;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тест атрибута \"О навыках\" формы заявки на стажировку")
class AboutSkillsTest {

  @InjectMocks
  private AboutSkills aboutSkillsAttribute;

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
  @DisplayName("Непустое значение -> OK")
  void givenNonNull_process_thenSuccess() {

    dto.setAboutSkills("Lorem ipsum");

    aboutSkillsAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getAboutSkills()
    ).isEqualTo("Lorem ipsum");
  }

  @Test
  @DisplayName("Пустая строка -> Exception")
  void givenEmpty_process_thenException() {

    final var expectedErrorMessage = I18n.ofKeys(
        "validation.error.attribute.value.missing",
        AboutSkills.TITLE_KEY
    );

    dto.setAboutSkills(StringUtils.EMPTY);

    final var exception = assertThrowsExactly(
        AttributeValueMissingException.class,
        () -> aboutSkillsAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Null -> Exception")
  void givenNull_process_thenException() {

    final var expectedErrorMessage = I18n.ofKeys(
        "validation.error.attribute.value.missing",
        AboutSkills.TITLE_KEY
    );

    dto.setAboutSkills(null);

    final var exception = assertThrowsExactly(
        AttributeValueMissingException.class,
        () -> aboutSkillsAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Свойства атриубта есть -> OK")
  void properties() {
    assertThat(
        aboutSkillsAttribute.properties()
    ).isNotNull();
  }

  @Test
  @DisplayName("Отображаемое имя задано -> OK")
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        aboutSkillsAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(AboutSkills.TITLE_KEY)
    );
  }

  @Test
  @DisplayName("Значение атрибута извлекается из DTO -> OK")
  void supplyInputValue() {

    dto.setAboutSkills("Lorem ipsum");

    final String result = aboutSkillsAttribute.supplyInputValue(dto);

    assertThat(result)
        .isEqualTo("Lorem ipsum");
  }

  @Test
  @DisplayName("Значение атрибута записывается в модель -> OK")
  void consumeOutputValue() {

    final var value = "Lorem ipsum";

    aboutSkillsAttribute.consumeOutputValue(value, entity);

    assertThat(entity.getAboutSkills())
        .isEqualTo("Lorem ipsum");
  }
}