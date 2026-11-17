package ru.rshb.intech.digital.feature.internship.core.service.registration.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.censor.forms.CensorAnalyzer;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.University;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.references.universities.core.repository.UniversityRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тест атрибута \"ВУЗ\" формы заявки на стажировку")
class UniversityTest {

  @Mock
  private UniversityRepository universityRepository;

  @Mock
  private CensorAnalyzer censorAnalyzer;

  @InjectMocks
  private University universityAttribute;

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
  @DisplayName("Значение из справочника по ID -> OK")
  void givenValid_process_thenSuccess() {

    final var universityFromDb = new ru.rshb.intech.digital.references.universities.entity.University();
    universityFromDb.setId(1L);
    universityFromDb.setExternalId("123");
    universityFromDb.setShortTitle("МГУ");
    universityFromDb.setFullTitle("Московский Государственный Университет");

    given(
        universityRepository.existsByExternalId("123")
    ).willReturn(true);

    dto.setUniversityId("123");

    universityAttribute.process(formContext);
  }

  @Test
  @DisplayName("Наименование ВУЗа не из справочника -> OK")
  void givenValidOnlyName_process_thenSuccess() {

    dto.setUniversityCustom("Квадрицепстаунский Политехнический Университет");

    universityAttribute.process(formContext);

    assertThat(entity.getUniversityId())
        .isNull();

    assertThat(entity.getUniversityCustom())
        .isEqualTo("Квадрицепстаунский Политехнический Университет");
  }

  @Test
  @DisplayName("Нет ни ID, ни наименования -> OK")
  void givenValidNull_process_thenSuccess() {

    dto.setUniversityId(null);
    dto.setUniversityCustom(null);

    universityAttribute.process(formContext);

    assertThat(entity.getUniversityId())
        .isNull();

    assertThat(entity.getUniversityCustom())
        .isNull();
  }

  @Test
  @DisplayName("ID ВУЗа, отсутствующего в справочнике -> Exception")
  void givenUnknownUniversityId_process_thenException() {

    final var expectedErrorMessage = I18n.of("internship.registration.error.validation.university.id.format");

    given(
        universityRepository.existsByExternalId("123")
    ).willReturn(false);

    dto.setUniversityId("123");

    final var exception = assertThrowsExactly(
        AttributeBusinessRulesException.class,
        () -> universityAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Свойства атриубта есть -> OK")
  void properties() {
    assertThat(
        universityAttribute.properties()
    ).isNotNull();
  }

  @Test
  @DisplayName("Отображаемое имя задано -> OK")
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        universityAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(University.TITLE_KEY)
    );
  }

  @Test
  @DisplayName("Значение атрибута извлекается из DTO -> OK")
  void supplyInputValue() {

    dto.setUniversityId("123");
    dto.setUniversityCustom("Test");

    final var result = universityAttribute.supplyInputValue(dto);

    assertThat(result)
        .isNotNull();

    assertThat(result.getId())
        .isEqualTo("123");

    assertThat(result.getName())
        .isEqualTo("Test");
  }

  @Test
  @DisplayName("Значение атрибута записывается в модель -> OK")
  void consumeOutputValue() {

    final var universityInfo = new University.UniversityInfo("123", null);

    universityAttribute.consumeOutputValue(universityInfo, entity);

    assertThat(entity.getUniversityId())
        .isEqualTo("123");

    assertThat(entity.getUniversityCustom())
        .isNull();
  }
}