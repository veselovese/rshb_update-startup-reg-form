package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.AgrotechOlympiadRegistrationDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadRegistration;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(MockitoExtension.class)
class VkProfileUrlTest {

  @InjectMocks
  private VkProfileUrl vkProfileUrlAttribute;
  private AgrotechOlympiadRegistration entity;

  @BeforeEach
  void setUp() {
    entity = new AgrotechOlympiadRegistration();
  }

  @Test
  void givenNotUrl_process_thenException() {

    final var testData = "abracadabra";

    final var context = new FormContext<>(
        "agrotechOlympiadRegistrationForm",
        new AgrotechOlympiadRegistrationDto(
            null,
            null,
            null,
            testData,
            null,
            null
        ),
        entity
    );

    final var exception = assertThrowsExactly(
        AttributeBadFormatException.class,
        () -> vkProfileUrlAttribute.process(context)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(I18n.of("validation.error.attribute.data.format", vkProfileUrlAttribute.properties().getName()));
  }

  @Test
  void givenVkUrl_process_thenSuccess() {

    final var testData = "https://vk.com/abracadabra";

    final var context = new FormContext<>(
        "agrotechOlympiadRegistrationForm",
        new AgrotechOlympiadRegistrationDto(
            null,
            null,
            null,
            testData,
            null,
            null
        ),
        entity
    );

    vkProfileUrlAttribute.process(context);

    assertThat(
        entity.getVkProfileUrl()
    ).isEqualTo(testData);
  }

  @Test
  void givenNull_process_thenSuccess() {

    final var context = new FormContext<>(
        "agrotechOlympiadRegistrationForm",
        new AgrotechOlympiadRegistrationDto(
            null,
            null,
            null,
            null,
            null,
            null
        ),
        entity
    );

    vkProfileUrlAttribute.process(context);

    assertThat(
        entity.getVkProfileUrl()
    ).isNull();
  }

  @Test
  void supplyInputValue() {

    final var dto = new AgrotechOlympiadRegistrationDto(
        null,
        null,
        null,
        "https://vk.com/abracadabra",
        null,
        null
    );

    final var suppliedValue = vkProfileUrlAttribute.supplyInputValue(dto);

    assertThat(suppliedValue)
        .isEqualTo(dto.vkProfileUrl());
  }

  @Test
  void consumeOutputValue() {

    final var testData = "https://vk.com/abracadabra";

    vkProfileUrlAttribute.consumeOutputValue(testData, entity);

    assertThat(
        entity.getVkProfileUrl()
    ).isEqualTo(testData);
  }
}
