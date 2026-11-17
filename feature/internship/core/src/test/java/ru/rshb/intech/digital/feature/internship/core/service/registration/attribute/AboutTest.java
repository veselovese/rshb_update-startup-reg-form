package ru.rshb.intech.digital.feature.internship.core.service.registration.attribute;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.About;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тест атрибута \"О себе\" формы заявки на стажировку")
class AboutTest {

  @InjectMocks
  private About aboutAttribute;

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
  @DisplayName("Непустая строка -> OK")
  void givenNonEmpty_process_thenSuccess() {

    dto.setDescription("Lorem ipsum");

    aboutAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getAbout()
    ).isEqualTo("Lorem ipsum");
  }

  @Test
  @DisplayName("Пустая строка -> OK")
  void givenEmpty_process_thenSuccess() {

    dto.setDescription(StringUtils.EMPTY);

    aboutAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getAbout()
    ).isNull();
  }

  @Test
  @DisplayName("Null -> OK")
  void givenNull_process_thenSuccess() {

    dto.setDescription(null);

    aboutAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getAbout()
    ).isNull();
  }

  @Test
  @DisplayName("Свойства атриубта есть -> OK")
  void properties() {
    assertThat(
        aboutAttribute.properties()
    ).isNotNull();
  }

  @Test
  @DisplayName("Отображаемое имя задано -> OK")
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        aboutAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(About.TITLE_KEY)
    );
  }

  @Test
  @DisplayName("Значение атрибута извлекается из DTO -> OK")
  void supplyInputValue() {

    dto.setDescription("Lorem ipsum");

    final String result = aboutAttribute.supplyInputValue(dto);

    assertThat(result)
        .isEqualTo("Lorem ipsum");
  }

  @Test
  @DisplayName("Значение атрибута записывается в модель -> OK")
  void consumeOutputValue() {

    final var value = "Lorem ipsum";

    aboutAttribute.consumeOutputValue(value, entity);

    assertThat(entity.getAbout())
        .isEqualTo("Lorem ipsum");
  }
}