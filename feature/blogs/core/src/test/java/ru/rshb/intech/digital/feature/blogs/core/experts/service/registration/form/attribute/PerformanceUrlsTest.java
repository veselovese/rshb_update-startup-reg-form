package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationFormData;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.messages.I18n;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PerformanceUrlsTest {

  @InjectMocks
  private PerformanceUrls performanceUrlsAttribute;

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
  void givenValidNonEmpty_process_thenSuccess() {

    final var testData = List.of(
        "https://www.youtube.com/watch?v=TFy_GXXo9mI&pp=ygUK0YXRg9GA0LzQsA%3D%3D",
        "https://www.youtube.com/watch?v=UhoabvL8FZg&pp=ygUK0YXRg9GA0LzQsA%3D%3D"
    );

    dto.setPerformanceUrls(testData);

    performanceUrlsAttribute.process(formContext);
  }

  @Test
  void givenValidEmpty_process_thenSuccess() {

    final var testData = Collections.<String>emptyList();

    dto.setPerformanceUrls(testData);

    performanceUrlsAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getPerformanceUrls()
    ).isNull();
  }

  @Test
  void givenValidNull_process_thenSuccess() {

    final List<String> testData = null;

    dto.setPerformanceUrls(testData);

    performanceUrlsAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getPerformanceUrls()
    ).isNull();
  }

  @Test
  void givenInvalidNonUrls_process_thenException() {

    final var testData = List.of(
        "https://www.youtube.com/watch?v=TFy_GXXo9mI&pp=ygUK0YXRg9GA0LzQsA%3D%3D",
        "abracadabra",
        "https://www.youtube.com/watch?v=UhoabvL8FZg&pp=ygUK0YXRg9GA0LzQsA%3D%3D"
    );

    dto.setPerformanceUrls(testData);

    final var exception = Assertions.assertThrowsExactly(
        AttributeBusinessRulesException.class,
        () -> performanceUrlsAttribute.process(formContext)
    );

    final var expectedErrorMessage = I18n.of("expert.registration.form.validation.error.applicant.performance.urls.format");

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  void properties() {
    assertThat(
        performanceUrlsAttribute.properties()
    ).isNotNull();
  }

  @Test
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        performanceUrlsAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(PerformanceUrls.TITLE_KEY)
    );
  }

  @Test
  void supplyInputValue() {

    final var testData = List.of(
        "https://www.youtube.com/watch?v=TFy_GXXo9mI&pp=ygUK0YXRg9GA0LzQsA%3D%3D",
        "https://www.youtube.com/watch?v=UhoabvL8FZg&pp=ygUK0YXRg9GA0LzQsA%3D%3D"
    );

    dto.setPerformanceUrls(testData);

    final List<String> ar = performanceUrlsAttribute.supplyInputValue(dto);

    assertThat(ar)
        .isEqualTo(testData);
  }

  @Test
  void consumeOutputValue() {

    final var testData = "https://www.youtube.com/watch?v=TFy_GXXo9mI&pp=ygUK0YXRg9GA0LzQsA%3D%3D" +
                         "," +
                         "https://www.youtube.com/watch?v=UhoabvL8FZg&pp=ygUK0YXRg9GA0LzQsA%3D%3D";

    final var er = String.join(",", testData);

    performanceUrlsAttribute.consumeOutputValue(testData, entity);

    assertThat(
        entity.getPerformanceUrls()
    ).isEqualTo(er);
  }
}