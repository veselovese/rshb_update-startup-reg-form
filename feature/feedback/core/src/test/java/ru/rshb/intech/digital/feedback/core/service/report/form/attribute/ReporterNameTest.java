package ru.rshb.intech.digital.feedback.core.service.report.form.attribute;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.feedback.core.dto.FeedbackFormData;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ReporterNameTest {

  @InjectMocks
  private ReporterName reporterNameAttribute;

  private FeedbackFormData dto;
  private FeedbackReport entity;
  private FormContext<FeedbackFormData, FeedbackReport> formContext;

  private String setTestData(String testData) {
    dto = new FeedbackFormData(testData, null, null, null, null);
    entity = new FeedbackReport();
    formContext = new FormContext<>("feedback", dto, entity);
    return testData;
  }

  @Test
  void givenFio_process_thenSuccess() {

    final var testData = setTestData("Кропоткин Петр Алексеевич");

    reporterNameAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getReporterName()
    ).isEqualTo(testData);
  }

  @Test
  void givenCompositeFio_process_thenSuccess() {

    final var testData = setTestData("Аббасов Фахраддин Фарман оглы");

    reporterNameAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getReporterName()
    ).isEqualTo(testData);
  }

  @Test
  void givenCompositeSurname_process_thenSuccess() {

    final var testData = setTestData("Муравьев-Апостол Сергей Иванович");

    reporterNameAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getReporterName()
    ).isEqualTo(testData);
  }

  @Test
  void givenSurnameAndName_process_thenSuccess() {

    final var testData = setTestData("Юлаев Салават");

    reporterNameAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getReporterName()
    ).isEqualTo(testData);
  }

  @Test
  void givenNoFio_process_thenSuccess() {

    setTestData(null);

    reporterNameAttribute.process(formContext);

    assertThat(
        entity.getReporterName()
    ).isNull();
  }

  @Test
  void givenBlankFio_process_thenSuccess() {

    setTestData(StringUtils.EMPTY);

    reporterNameAttribute.process(formContext);

    assertThat(
        entity.getReporterName()
    ).isNull();
  }

  @Test
  void properties() {
    assertThat(
        reporterNameAttribute.properties()
    ).isNotNull();
  }

  @Test
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        reporterNameAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(ReporterName.TITLE_KEY)
    );
  }

  @Test
  void supplyInputValue() {

    final var testData = setTestData("Иванов Иван Иванович");

    final String result = reporterNameAttribute.supplyInputValue(dto);

    assertThat(result)
        .isEqualTo(testData);
  }

  @Test
  void consumeOutputValue() {

    final var testData = setTestData("Иванов Иван Иванович");

    reporterNameAttribute.consumeOutputValue(testData, entity);

    assertThat(
        entity.getReporterName()
    ).isEqualTo(testData);
  }
}
