package ru.rshb.intech.digital.feedback.core.service.report.form.attribute;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.attributes.phone.PhoneAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.feedback.core.dto.FeedbackFormData;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(MockitoExtension.class)
class PhoneTest {

  @InjectMocks
  private Phone phoneAttribute;

  private FeedbackFormData dto;
  private FeedbackReport entity;
  private FormContext<FeedbackFormData, FeedbackReport> formContext;

  private String setTestData(String testData) {
    dto = new FeedbackFormData(null, null, testData, null, null);
    entity = new FeedbackReport();
    formContext = new FormContext<>("feedback", dto, entity);
    return testData;
  }

  @Test
  void givenValid_process_thenSuccess() {

    setTestData("+7 (999) 666-42-42");

    phoneAttribute.process(formContext);

    assertThat(
        entity.getPhone()
    ).isEqualTo("+79996664242");
  }

  @Test
  void givenInvalidFormat_process_thenSuccess() {

    setTestData("abracadabra");

    final var exception = assertThrowsExactly(
        AttributeBadFormatException.class,
        () -> phoneAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.of("validation.error.attribute.data.format", phoneAttribute.properties().getName())
    );
  }

  @Test
  void givenEmpty_process_thenSuccess() {

    setTestData(StringUtils.EMPTY);

    phoneAttribute.process(formContext);

    assertThat(
        entity.getPhone()
    ).isNull();
  }

  @Test
  void givenNull_process_thenSuccess() {

    setTestData(null);

    phoneAttribute.process(formContext);

    assertThat(
        entity.getPhone()
    ).isNull();
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

    final var testData = setTestData("+7 (999) 666-42-42");

    final String result = phoneAttribute.supplyInputValue(dto);

    assertThat(result)
        .isEqualTo(testData);
  }

  @Test
  void consumeOutputValue() {

    final var testData = setTestData("+79996664242");

    phoneAttribute.consumeOutputValue(testData, entity);

    assertThat(
        entity.getPhone()
    ).isEqualTo(testData);
  }
}