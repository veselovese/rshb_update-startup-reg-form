package ru.rshb.intech.digital.feedback.core.service.report.form.attribute;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeMaxLengthExceededException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.feedback.core.dto.FeedbackFormData;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(MockitoExtension.class)
class FeedbackTest {

  @InjectMocks
  private Feedback feedbackAttribute;

  private FeedbackFormData dto;
  private FeedbackReport entity;
  private FormContext<FeedbackFormData, FeedbackReport> formContext;

  private String setTestData(String testData) {
    dto = new FeedbackFormData(null, null, null, testData, null);
    entity = new FeedbackReport();
    formContext = new FormContext<>("feedback", dto, entity);
    return testData;
  }

  @Test
  void givenValid_process_thenSuccess() {

    final var testData = setTestData("Lorem ipsum");

    feedbackAttribute.process(formContext);

    assertThat(
        entity.getFeedback()
    ).isEqualTo(testData);
  }

  @Test
  void givenEmpty_process_thenException() {

    setTestData(StringUtils.EMPTY);

    final var exception = assertThrowsExactly(
        AttributeValueMissingException.class,
        () -> feedbackAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.of("validation.error.attribute.value.missing", feedbackAttribute.properties().getName())
    );
  }

  @Test
  void givenNull_process_thenException() {

    setTestData(null);

    final var exception = assertThrowsExactly(
        AttributeValueMissingException.class,
        () -> feedbackAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.of("validation.error.attribute.value.missing", feedbackAttribute.properties().getName())
    );
  }

  @Test
  void givenLengthExceeded_process_thenException() {

    setTestData(
        StringUtils.repeat("a", FeedbackReport.FEEDBACK_LENGTH + 1)
    );

    final var exception = assertThrowsExactly(
        AttributeMaxLengthExceededException.class,
        () -> feedbackAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.of("validation.error.attribute.max.length.exceeded", feedbackAttribute.properties().getName())
    );
  }

  @Test
  void properties() {
    assertThat(
        feedbackAttribute.properties()
    ).isNotNull();
  }

  @Test
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        feedbackAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(Feedback.TITLE_KEY)
    );
  }

  @Test
  void supplyInputValue() {

    final var testData = setTestData("Lorem ipsum");

    final String result = feedbackAttribute.supplyInputValue(dto);

    assertThat(result)
        .isEqualTo(testData);
  }

  @Test
  void consumeOutputValue() {

    final var testData = setTestData("Lorem ipsum");

    feedbackAttribute.consumeOutputValue(testData, entity);

    assertThat(
        entity.getFeedback()
    ).isEqualTo(testData);
  }
}
