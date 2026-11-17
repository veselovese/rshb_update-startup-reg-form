package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationFormData;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.ExpertFieldService;
import ru.rshb.intech.digital.messages.I18n;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DirectionsTest {

  @Mock
  private ExpertFieldService expertFieldService;

  @InjectMocks
  private Directions directionsAttribute;

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
  void givenNonEmptyFromRef_process_thenSuccess() {

    final var testData = List.of(
        "Технологии",
        "Маркетинг"
    );

    given(
        expertFieldService.isFieldExist(eq("Технологии"))
    ).willReturn(true);

    given(
        expertFieldService.isFieldExist(eq("Маркетинг"))
    ).willReturn(true);

    dto.setDirections(testData);

    directionsAttribute.process(formContext);
  }

  @Test
  void givenNonEmptyMixed_process_thenSuccess() {

    final var testData = List.of(
        "Технологии",
        "Маркетинг",
        "Хурмоварение"
    );

    given(
        expertFieldService.isFieldExist(eq("Технологии"))
    ).willReturn(true);

    given(
        expertFieldService.isFieldExist(eq("Маркетинг"))
    ).willReturn(true);

    given(
        expertFieldService.isFieldExist(eq("Хурмоварение"))
    ).willReturn(false);

    dto.setDirections(testData);

    directionsAttribute.process(formContext);
  }

  @Test
  void givenEmpty_process_thenException() {

    final var testData = Collections.<String>emptyList();

    dto.setDirections(testData);

    final var exception = assertThrowsExactly(
        AttributeBusinessRulesException.class,
        () -> directionsAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.of("expert.registration.form.validation.error.applicant.field.missing")
    );
  }

  @Test
  void givenNull_process_thenException() {

    final List<String> testData = null;

    dto.setDirections(testData);

    final var exception = assertThrowsExactly(
        AttributeValueMissingException.class,
        () -> directionsAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.ofKeys(
            "validation.error.attribute.value.missing",
            "expert.registration.form.attribute.directions"
        )
    );
  }

  @Test
  void givenAFewNonExisting_process_thenException() {

    final var testData = List.of(
        "Технологии",
        "Тыквоварение",
        "Хурмоварение"
    );

    given(
        expertFieldService.isFieldExist(eq("Технологии"))
    ).willReturn(true);

    given(
        expertFieldService.isFieldExist(eq("Тыквоварение"))
    ).willReturn(false);

    given(
        expertFieldService.isFieldExist(eq("Хурмоварение"))
    ).willReturn(false);

    dto.setDirections(testData);

    final var exception = assertThrowsExactly(
        AttributeBusinessRulesException.class,
        () -> directionsAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.of("expert.registration.form.validation.error.applicant.field.bad.value")
    );
  }

  @Test
  void givenOneOnlyNonExisting_process_thenSuccess() {

    final var testData = List.of("Хурмоварение");

    given(
        expertFieldService.isFieldExist(eq("Хурмоварение"))
    ).willReturn(false);

    dto.setDirections(testData);

    directionsAttribute.process(formContext);
  }

  @Test
  void properties() {
    assertThat(
        directionsAttribute.properties()
    ).isNotNull();
  }

  @Test
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        directionsAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(Directions.TITLE_KEY)
    );
  }

  @Test
  void supplyInputValue() {

    final var testData = List.of(
        "Технологии",
        "Маркетинг"
    );

    dto.setDirections(testData);

    final List<String> ar = directionsAttribute.supplyInputValue(dto);

    assertThat(ar)
        .isEqualTo(testData);
  }

  @Test
  void consumeOutputValue() {

    final var testData = "Технологии,Маркетинг";
    final var er = "Технологии,Маркетинг";

    directionsAttribute.consumeOutputValue(testData, entity);

    assertThat(entity.getDirections())
        .isEqualTo(er);
  }
}