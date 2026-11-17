package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeMaxLengthExceededException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationFormData;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(MockitoExtension.class)
class CommentTest {

  @InjectMocks
  private Comment commentAttribute;

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
  void givenValid_process_thenSuccess() {

    final var testData = "Lorem ipsum";

    dto.setComment(testData);

    commentAttribute.process(formContext);

    assertThat(
        entity.getComment()
    ).isEqualTo(testData);
  }

  @Test
  void givenEmpty_process_thenException() {

    final var testData = StringUtils.EMPTY;

    dto.setComment(testData);

    final var exception = assertThrowsExactly(
        AttributeValueMissingException.class,
        () -> commentAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.of("validation.error.attribute.value.missing", commentAttribute.properties().getName())
    );
  }

  @Test
  void givenNull_process_thenException() {

    final String testData = null;

    dto.setComment(testData);

    final var exception = assertThrowsExactly(
        AttributeValueMissingException.class,
        () -> commentAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.of("validation.error.attribute.value.missing", commentAttribute.properties().getName())
    );
  }

  @Test
  void givenTooLong_process_thenException() {

    final var testData = StringUtils.repeat("a", Comment.MAX_LENGTH + 1);

    dto.setComment(testData);

    final var exception = assertThrowsExactly(
        AttributeMaxLengthExceededException.class,
        () -> commentAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.of("validation.error.attribute.max.length.exceeded", commentAttribute.properties().getName())
    );
  }

  @Test
  void properties() {
    assertThat(
        commentAttribute.properties()
    ).isNotNull();
  }

  @Test
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        commentAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(Comment.TITLE_KEY)
    );
  }

  @Test
  void supplyInputValue() {

    final var testData = "Lorem ipsum";

    dto.setComment(testData);

    final String ar = commentAttribute.supplyInputValue(dto);

    assertThat(ar)
        .isEqualTo(testData);
  }

  @Test
  void consumeOutputValue() {

    final var testData = "Иванов Иван Иванович";

    commentAttribute.consumeOutputValue(testData, entity);

    assertThat(
        entity.getComment()
    ).isEqualTo(testData);
  }
}