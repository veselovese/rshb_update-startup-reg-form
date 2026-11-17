package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.attributes.vk.VkUrlAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationFormData;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(MockitoExtension.class)
class VkProfileUrlTest {

  @InjectMocks
  private VkProfileUrl vkProfileUrlAttribute;

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
  void properties() {
    assertThat(
        vkProfileUrlAttribute.properties()
    ).isNotNull();
  }

  @Test
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        vkProfileUrlAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(VkUrlAttributeBase.TITLE_KEY)
    );
  }

  @Test
  void givenEmpty_process_thenOk() {

    final var testData = StringUtils.EMPTY;

    dto.setVkProfileUrl(testData);

    vkProfileUrlAttribute.process(formContext);
  }

  @Test
  void givenNull_process_thenOk() {

    final String testData = null;

    dto.setVkProfileUrl(testData);

    vkProfileUrlAttribute.process(formContext);
  }

  @Test
  void givenValidUrlCom_process_thenOk() {

    final var testData = "https://vk.com/abracadabra";

    dto.setVkProfileUrl(testData);

    vkProfileUrlAttribute.process(formContext);
  }

  @Test
  void givenValidUrlRu_process_thenOk() {

    final var testData = "https://vk.rus/abracadabra";

    dto.setVkProfileUrl(testData);

    vkProfileUrlAttribute.process(formContext);
  }

  @Test
  void givenInvalidUrl_process_thenException() {

    final var testData = "https://vk.rus/   abracadabra";

    dto.setVkProfileUrl(testData);

    final var exception = assertThrowsExactly(
        AttributeBadFormatException.class,
        () -> vkProfileUrlAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.ofKeys(
            "validation.error.attribute.data.format",
            VkUrlAttributeBase.TITLE_KEY
        )
    );
  }

  @Test
  void givenAnyWork_process_thenException() {

    final var testData = "abracadabra";

    dto.setVkProfileUrl(testData);

    final var exception = assertThrowsExactly(
        AttributeBadFormatException.class,
        () -> vkProfileUrlAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.ofKeys(
            "validation.error.attribute.data.format",
            VkUrlAttributeBase.TITLE_KEY
        )
    );
  }

  @Test
  void supplyInputValue() {

    final var testData = "https://vk.com/abracadabra";

    dto.setVkProfileUrl(testData);

    final String ar = vkProfileUrlAttribute.supplyInputValue(dto);

    assertThat(ar)
        .isEqualTo(testData);
  }

  @Test
  void consumeOutputValue() {

    final var testData = "https://vk.com/abracadabra";

    vkProfileUrlAttribute.consumeOutputValue(testData, entity);

    assertThat(entity.getVkUrl())
        .isEqualTo(testData);
  }
}