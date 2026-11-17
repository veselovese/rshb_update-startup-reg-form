package ru.rshb.intech.digital.feature.internship.core.service.registration.attribute;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute.Telegram;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тест атрибута \"Telegram\" формы заявки на стажировку")
class TelegramTest {

  @InjectMocks
  private Telegram nullableTelegramAttribute;

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
  @DisplayName("Полная ссылка на профиль -> OK")
  void givenValidFullLink_process_thenSuccess() {

    dto.setTelegram("https://t.me/XNadoricheva");

    nullableTelegramAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getTelegram()
    ).isEqualTo("https://t.me/XNadoricheva");
  }

  @Test
  @DisplayName("Юзернейм -> OK")
  void givenUsername_process_thenSuccess() {

    dto.setTelegram("@test");

    nullableTelegramAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getTelegram()
    ).isEqualTo("@test");
  }

  @Test
  @DisplayName("Не Telegram -> Exception")
  void givenNonTelegram_process_thenException() {

    final var expectedErrorMessage = I18n.ofKeys(
        "validation.error.attribute.data.format",
        Telegram.TITLE_KEY
    );

    dto.setTelegram("abracadabra");

    final var exception = assertThrowsExactly(
        AttributeBadFormatException.class,
        () -> nullableTelegramAttribute.process(formContext)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(expectedErrorMessage);
  }

  @Test
  @DisplayName("Nullable: Пустая строка  -> OK")
  void givenNullableEmpty_process_thenSuccess() {

    dto.setTelegram(StringUtils.EMPTY);

    nullableTelegramAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getTelegram()
    ).isNull();
  }

  @Test
  @DisplayName("Nullable: Null -> OK")
  void givenNullableNull_process_thenSuccess() {

    dto.setTelegram(null);

    nullableTelegramAttribute.process(formContext);

    assertThat(
        formContext.getOutputData().getTelegram()
    ).isNull();
  }

  @Test
  @DisplayName("Свойства атриубта есть -> OK")
  void properties() {
    assertThat(
        nullableTelegramAttribute.properties()
    ).isNotNull();
  }

  @Test
  @DisplayName("Отображаемое имя задано -> OK")
  void givenValidAttrName_properties_thenOk() {
    assertThat(
        nullableTelegramAttribute.properties().getName()
    ).isEqualTo(
        I18n.of(Telegram.TITLE_KEY)
    );
  }

  @Test
  @DisplayName("Значение атрибута извлекается из DTO -> OK")
  void supplyInputValue() {

    dto.setTelegram("https://t.me/XNadoricheva");

    final String result = nullableTelegramAttribute.supplyInputValue(dto);

    assertThat(result)
        .isEqualTo("https://t.me/XNadoricheva");
  }

  @Test
  @DisplayName("Значение атрибута записывается в модель -> OK")
  void consumeOutputValue() {

    final var telegram = "https://t.me/XNadoricheva";

    nullableTelegramAttribute.consumeOutputValue(telegram, entity);

    assertThat(
        entity.getTelegram()
    ).isEqualTo("https://t.me/XNadoricheva");
  }
}