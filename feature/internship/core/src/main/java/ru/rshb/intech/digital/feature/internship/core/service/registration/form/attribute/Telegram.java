package ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.DataFormatValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeParams;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.commons.validation.telegram.TelegramUtils;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.InternshipRegistrationForm;
import ru.rshb.intech.digital.messages.I18n;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Атрибут "Telegram".
 * Форма подачи заявки на стажировку.
 */
@Component("internshipRegistrationTelegram")
@FormAttribute(InternshipRegistrationForm.class)
public class Telegram extends TextAttributeBase<InternshipRegistrationDto, Registration> {

  public static final String TITLE_KEY = "internship.registration.form.attribute.telegram";


  public Telegram() {
    super(
        TextAttributeParams.Builder
            .<InternshipRegistrationDto, Registration>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(true)
            .maxLength(Registration.TELEGRAM_MAX_LEN)
            .dataFormatValidator(new TelegramDataFormatValidator<>())
            .build()
    );
  }

  @Override
  public String supplyInputValue(@Nonnull InternshipRegistrationDto inputData) {
    return inputData.getTelegram();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull Registration outputData) {
    outputData.setTelegram(outputValue);
  }

  @RequiredArgsConstructor
  private static class TelegramDataFormatValidator<D, E> implements DataFormatValidator<D, String, E> {

    @Override
    public <A extends AttributeParams<D, String, E, ?>> void validate(String value,
                                                                      @Nonnull A attrProps,
                                                                      @Nonnull FormContext<D, E> context)
        throws AttributeValueMissingException, AttributeBadFormatException {


      if (attrProps.isNullable() && isBlank(value)) {
        return;
      }

      if (TelegramUtils.isNotValidLink(value)) {
        throw new AttributeBadFormatException(attrProps.getName());
      }
    }
  }
}
