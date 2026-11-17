package ru.rshb.intech.digital.feature.internship.core.service.registration.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.repository.fields.InternshipFieldRepository;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.InternshipFields;
import ru.rshb.intech.digital.messages.I18n;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тест атрибута \"Направление\" формы заявки на стажировку")
class InternshipFieldsTest {

  @Mock
  private InternshipFieldRepository fieldRepository;

  @InjectMocks
  private InternshipFields internshipFieldsAttribute;

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
  @DisplayName("Значения из справчника через запятую -> OK")
  void givenValidFields_process_thenSuccess() {

    given(
        fieldRepository.existsAllByTitleIsIn(Set.of("Разработка", "Тестирование"))
    ).willReturn(true);

    dto.setInternshipField("Разработка,Тестирование");

    internshipFieldsAttribute.process(formContext);
  }

  @Test
  @DisplayName("Значения не из справочника -> Exception")
  void givenUnknownFields_process_thenException() {

    final var expectedErrorMessage = I18n.of("internship.registration.error.validation.internship.field.unknown");

    given(
        fieldRepository.existsAllByTitleIsIn(Set.of("Abracadabra"))
    ).willReturn(false);

    dto.setInternshipField("Abracadabra");

    final var exception = assertThrowsExactly(
        AttributeBusinessRulesException.class,
        () -> internshipFieldsAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Свойства атриубта есть -> OK")
  void properties() {
    assertThat(internshipFieldsAttribute.properties()).isNotNull();
  }

  @Test
  @DisplayName("Отображаемое имя задано -> OK")
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        internshipFieldsAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(InternshipFields.TITLE_KEY)
    );
  }


  @Test
  @DisplayName("Значение атрибута извлекается из DTO -> OK")
  void supplyInputValue() {

    dto.setInternshipField("Разработка,Тестирование");

    final String result = internshipFieldsAttribute.supplyInputValue(dto);

    assertThat(result)
        .isEqualTo("Разработка,Тестирование");
  }

  @Test
  @DisplayName("Значение атрибута записывается в модель -> OK")
  void consumeOutputValue() {

    final String value = "Разработка,Тестирование";

    internshipFieldsAttribute.consumeOutputValue(value, entity);

    assertThat(entity.getInternshipField())
        .isEqualTo("Разработка,Тестирование");
  }
}
