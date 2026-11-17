package ru.rshb.intech.digital.feature.internship.core.service.registration.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.repository.education.EducationStageRepository;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.EducationStage;
import ru.rshb.intech.digital.messages.I18n;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тест атрибута \"Уровень образования\" формы заявки на стажировку")
class EducationStageTest {

  @Mock
  private EducationStageRepository educationStageRepository;

  @InjectMocks
  private EducationStage educationStageAttribute;

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
  @DisplayName("Уровень образования из справочника и соответствующий год обучения -> OK")
  void givenValidNonNull_process_thenSuccess() {

    final var stageInDB = new ru.rshb.intech.digital.feature.internship.core.entity.EducationStage();
    stageInDB.setId(1L);
    stageInDB.setExternalId("7a53ae82-029e-41ab-863a-44f2ff175305");
    stageInDB.setTitle("специалитет");
    stageInDB.setMinYear(1);
    stageInDB.setMaxYear(3);
    stageInDB.setEnabled(true);

    given(educationStageRepository.findByTitleIgnoreCase("специалитет"))
        .willReturn(Optional.of(stageInDB));

    dto.setEducationStage("специалитет");
    dto.setEducationStageYear(3);

    educationStageAttribute.process(formContext);

    assertThat(
        entity.getEducationStage()
    ).isEqualTo(stageInDB.getTitle());

    assertThat(
        entity.getEducationYear()
    ).isEqualTo(
        dto.getEducationStageYear()
    );
  }

  @Test
  @DisplayName("Нет уровня образования и года обучения -> OK")
  void givenValidNull_process_thenSuccess() {

    dto.setEducationStage(null);
    dto.setEducationStageYear(null);

    educationStageAttribute.process(formContext);

    assertThat(
        entity.getEducationStage()
    ).isNull();

    assertThat(
        entity.getEducationYear()
    ).isNull();
  }

  @Test
  @DisplayName("Уровень образования без года обучения -> OK")
  void givenValidOnlyStageName_process_thenSuccess() {

    final var stageInDB = new ru.rshb.intech.digital.feature.internship.core.entity.EducationStage();
    stageInDB.setId(1L);
    stageInDB.setExternalId("747e93ea-987e-469c-b439-c9626f5e326a");
    stageInDB.setTitle("выпускник");
    stageInDB.setMinYear(null);
    stageInDB.setMaxYear(null);
    stageInDB.setEnabled(true);

    given(educationStageRepository.findByTitleIgnoreCase("выпускник"))
        .willReturn(Optional.of(stageInDB));

    dto.setEducationStage("выпускник");
    dto.setEducationStageYear(null);

    educationStageAttribute.process(formContext);

    assertThat(
        entity.getEducationStage()
    ).isEqualTo(stageInDB.getTitle());

    assertThat(
        entity.getEducationYear()
    ).isNull();
  }

  @Test
  @DisplayName("Отрицательный год -> Exception")
  void givenNegativeYear_process_thenException() {

    final var expectedErrorMessage = I18n.ofKeys(
        "validation.error.attribute.data.format",
        "internship.registration.form.field.education.year"
    );

    dto.setEducationStageYear(-1);

    final var exception = assertThrowsExactly(AttributeBadFormatException.class, () -> {
      educationStageAttribute.process(formContext);
    });

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Год обучения больше макс. допустимого для данного уровня образования -> OK")
  void givenYearGreaterThanAcceptable_process_thenException() {

    final var expectedErrorMessage = I18n.of(
        "internship.registration.error.validation.education.year"
    );

    final var stageInDB = new ru.rshb.intech.digital.feature.internship.core.entity.EducationStage();
    stageInDB.setId(1L);
    stageInDB.setExternalId("7a53ae82-029e-41ab-863a-44f2ff175305");
    stageInDB.setTitle("специалитет");
    stageInDB.setMinYear(1);
    stageInDB.setMaxYear(3);
    stageInDB.setEnabled(true);

    given(educationStageRepository.findByTitleIgnoreCase("специалитет"))
        .willReturn(Optional.of(stageInDB));

    dto.setEducationStage("специалитет");
    dto.setEducationStageYear(5);

    final var exception = assertThrowsExactly(AttributeBusinessRulesException.class, () -> {
      educationStageAttribute.process(formContext);
    });

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Год обучения меньше мин. допустимого для данного уровня образования -> OK")
  void givenYearLessThanAcceptable_process_thenException() {

    final var expectedErrorMessage = I18n.of(
        "internship.registration.error.validation.education.year"
    );

    final var stageInDB = new ru.rshb.intech.digital.feature.internship.core.entity.EducationStage();
    stageInDB.setId(1L);
    stageInDB.setExternalId("7a53ae82-029e-41ab-863a-44f2ff175305");
    stageInDB.setTitle("специалитет");
    stageInDB.setMinYear(2);
    stageInDB.setMaxYear(3);
    stageInDB.setEnabled(true);

    given(educationStageRepository.findByTitleIgnoreCase("специалитет"))
        .willReturn(Optional.of(stageInDB));

    dto.setEducationStage("специалитет");
    dto.setEducationStageYear(1);

    final var exception = assertThrowsExactly(AttributeBusinessRulesException.class, () -> {
      educationStageAttribute.process(formContext);
    });

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Уровень образования не из справочника -> Exception")
  void givenUnknownStageName_process_thenException() {

    final var expectedErrorMessage = I18n.of(
        "internship.registration.error.validation.education.stage"
    );

    dto.setEducationStage("abracadabra");

    final var exception = assertThrowsExactly(AttributeBusinessRulesException.class, () -> {
      educationStageAttribute.process(formContext);
    });

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }


  @Test
  @DisplayName("Свойства атриубта есть -> OK")
  void properties() {
    assertThat(
        educationStageAttribute.properties()
    ).isNotNull();
  }

  @Test
  @DisplayName("Отображаемое имя задано -> OK")
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        educationStageAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(EducationStage.TITLE_KEY)
    );
  }

  @Test
  @DisplayName("Значение атрибута извлекается из DTO -> OK")
  void supplyInputValue() {

    dto.setEducationStage("специалитет");
    dto.setEducationStageYear(3);

    final EducationStage.EducationStageInfo educationStageInfo = educationStageAttribute.supplyInputValue(dto);

    assertThat(
        educationStageInfo.getStage()
    ).isEqualTo("специалитет");

    assertThat(
        educationStageInfo.getYear()
    ).isEqualTo(3);
  }

  @Test
  @DisplayName("Значение атрибута записывается в модель -> OK")
  void consumeOutputValue() {

    final var eduInfo = new EducationStage.EducationStageInfo("специалитет", 3);

    educationStageAttribute.consumeOutputValue(eduInfo, entity);

    assertThat(
        entity.getEducationStage()
    ).isEqualTo("специалитет");

    assertThat(
        entity.getEducationYear()
    ).isEqualTo(3);
  }
}