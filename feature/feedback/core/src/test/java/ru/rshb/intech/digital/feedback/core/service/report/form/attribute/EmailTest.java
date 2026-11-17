package ru.rshb.intech.digital.feedback.core.service.report.form.attribute;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.attributes.email.EmailAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.feedback.core.FeedbackOptions;
import ru.rshb.intech.digital.feedback.core.dto.FeedbackFormData;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;
import ru.rshb.intech.digital.feedback.core.service.FeedbackService;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EmailTest {

  @Mock
  private FeedbackOptions feedbackOptions;

  @Mock
  private FeedbackService feedbackService;

  @InjectMocks
  private Email emailAttribute;

  private FeedbackFormData dto;
  private FeedbackReport entity;
  private FormContext<FeedbackFormData, FeedbackReport> formContext;

  private String setTestData(String testData) {
    dto = new FeedbackFormData(null, testData, null, null, null);
    entity = new FeedbackReport();
    formContext = new FormContext<>("feedback", dto, entity);
    return testData;
  }

  @Test
  void givenValid_process_thenSuccess() {

    final var testData = setTestData("test@test.te");

    given(
        feedbackOptions.maxReportsCountPerInterval()
    ).willReturn(5);

    given(
        feedbackService.getReportsNumberByEmail(anyString())
    ).willReturn(0L);

    emailAttribute.process(formContext);

    assertThat(
        entity.getEmail()
    ).isEqualTo(testData);
  }

  @Test
  void givenMaxExistingReportsCount_thenException() {

    setTestData("test@test.te");

    given(
        feedbackOptions.maxReportsCountPerInterval()
    ).willReturn(3);

    given(
        feedbackOptions.intervalHours()
    ).willReturn(48);

    given(
        feedbackService.getReportsNumberByEmail(anyString())
    ).willReturn(3L);

    final var exception = Assertions.assertThrowsExactly(
        AttributeBusinessRulesException.class,
        () -> emailAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.of("feedback.report.error.validation.limit.exceeded", 48)
    );
  }

  @Test
  void givenMaxExistingReportsCountExceeded_thenException() {

    setTestData("test@test.te");
    final var CHECKING_INTERVAL_HOURS = 48;
    final var MAX_REPORT_COUNT_PER_INTERVAL = 3;
    final var EXISTING_REPORTS_NUMBER = 4L;

    given(
        feedbackOptions.maxReportsCountPerInterval()
    ).willReturn(MAX_REPORT_COUNT_PER_INTERVAL);

    given(
        feedbackOptions.intervalHours()
    ).willReturn(CHECKING_INTERVAL_HOURS);

    given(
        feedbackService.getReportsNumberByEmail(anyString())
    ).willReturn(EXISTING_REPORTS_NUMBER);

    final var exception = Assertions.assertThrowsExactly(
        AttributeBusinessRulesException.class,
        () -> emailAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.of("feedback.report.error.validation.limit.exceeded", CHECKING_INTERVAL_HOURS)
    );
  }


  @Test
  void givenInvalidFormat_process_thenException() {

    setTestData("abracadabra");

    final var exception = Assertions.assertThrowsExactly(
        AttributeBadFormatException.class,
        () -> emailAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.of("validation.error.attribute.data.format", emailAttribute.properties().getName())
    );
  }

  @Test
  void givenEmpty_process_thenException() {

    setTestData(StringUtils.EMPTY);

    final var exception = Assertions.assertThrowsExactly(
        AttributeValueMissingException.class,
        () -> emailAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.of("validation.error.attribute.value.missing", emailAttribute.properties().getName())
    );
  }

  @Test
  void givenNull_process_thenException() {

    setTestData(null);

    final var exception = Assertions.assertThrowsExactly(
        AttributeValueMissingException.class,
        () -> emailAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.of("validation.error.attribute.value.missing", emailAttribute.properties().getName())
    );
  }


  @Test
  void properties() {
    assertThat(
        emailAttribute.properties()
    ).isNotNull();
  }

  @Test
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        emailAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(EmailAttributeBase.DEFAULT_TITLE_KEY)
    );
  }

  @Test
  void supplyInputValue() {

    final var testData = setTestData("test@test.te");

    final String result = emailAttribute.supplyInputValue(dto);

    assertThat(result)
        .isEqualTo(testData);
  }

  @Test
  void consumeOutputValue() {

    final var testData = setTestData("test@test.te");

    emailAttribute.consumeOutputValue(testData, entity);

    assertThat(
        entity.getEmail()
    ).isEqualTo(testData);
  }
}
