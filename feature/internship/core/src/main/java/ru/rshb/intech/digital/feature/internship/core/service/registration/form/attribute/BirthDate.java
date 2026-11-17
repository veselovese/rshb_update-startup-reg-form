package ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.DataFormatValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.exception.RegistrationValidationException;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.InternshipRegistrationForm;
import ru.rshb.intech.digital.messages.I18n;

import java.time.LocalDate;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE;
import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeUtilz.isNotSimpleDate;

/**
 * Атрибут "Дата рождения".
 * Форма подачи заявки на стажировку.
 */
@Component("internshipRegistrationBirthDate")
@FormAttribute(InternshipRegistrationForm.class)
public class BirthDate extends InternshipRegistrationAttribute<String, LocalDate> { // TODO сделать типовой компонент для дат

  public static final String TITLE_KEY = "internship.registration.form.field.birthdate";
  private static final String TITLE = I18n.of(TITLE_KEY);

  public BirthDate() {
    super(
        AttributeParams.Builder
            .<InternshipRegistrationDto, String, Registration, LocalDate>create()
            .name(TITLE)
            .dataFormatValidator(new BirthDateDataFormatValidator<>())
            .valueMapper(new BirthDateMapper())
            .businessRulesValidator(new BirthDateValidator())
            .build()
    );
  }


  @Override
  public String supplyInputValue(@Nonnull InternshipRegistrationDto inputData) {
    return inputData.getBirthDate();
  }

  @Override
  public void consumeOutputValue(LocalDate outputValue, @Nonnull Registration outputData) {
    outputData.setBirthDate(outputValue);
  }

  private static class BirthDateDataFormatValidator<D, E> implements DataFormatValidator<D,String, E> {


    @Override
    public <A extends AttributeParams<D, String, E, ?>> void validate(String value,
                                                                      @Nonnull A attrProps,
                                                                      @Nonnull FormContext<D, E> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      if (isBlank(value)) {
        throw new AttributeValueMissingException(attrProps.getName());
      }

      if (isNotSimpleDate(value)) {
        throw new AttributeBadFormatException(attrProps.getName());
      }
    }
  }

  private static class BirthDateMapper implements ValueMapper<String, LocalDate> {

    @Override
    public <D, E> LocalDate map(String rawValue, @Nonnull FormContext<D, E> context) {
      return LocalDate.parse(rawValue, SIMPLE_DATE.getFormatter());
    }
  }

  private static class BirthDateValidator
      implements BusinessRulesValidator<InternshipRegistrationDto, Registration, LocalDate> {

    @Override
    public void validate(LocalDate value,
                         @Nonnull FormContext<InternshipRegistrationDto, Registration> context)
        throws AttributeBusinessRulesException {

      final var now = LocalDate.now();

      final var isFutureDate = (value.isAfter(now) || value.isEqual(now));

      if (isFutureDate) {
        throw RegistrationValidationException.invalidBirthDate();
      }
    }
  }
}
