package ru.rshb.intech.digital.feature.contests.patent.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.Attribute;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.DataFormatValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.number.IntegerAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.commons.utils.datetime.DateTimeUtilz;
import ru.rshb.intech.digital.feature.contests.patent.dto.PatentContestRegistrationRequestDto;
import ru.rshb.intech.digital.feature.contests.patent.entity.PatentContestRegistration;
import ru.rshb.intech.digital.feature.contests.patent.service.registration.form.PatentContestRegistrationForm;
import ru.rshb.intech.digital.messages.I18n;

/**
 * Атрибут "Год патентирования".
 * Форма регистрации участника в конкурсе патентов.
 */
@Component
@FormAttribute(PatentContestRegistrationForm.class)
public class PatentYear extends Attribute<PatentContestRegistrationRequestDto, Integer, PatentContestRegistration, Integer> {

  private static final String TITLE_KEY = "patents.contest.registration.form.attribute.patent.year";

  public PatentYear() {
    super(
        AttributeParams.Builder
            .<PatentContestRegistrationRequestDto, Integer, PatentContestRegistration, Integer>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(false)
            .dataFormatValidator(new PatentYearDataFormatValidator<>())
            .valueMapper(new IntegerAttributeBase.ThroughValueMapper())
            .build()
    );
  }

  @Override
  public Integer supplyInputValue(@Nonnull PatentContestRegistrationRequestDto inputData) {
    return inputData.getPatentYear();
  }

  @Override
  public void consumeOutputValue(Integer outputValue, @Nonnull PatentContestRegistration outputData) {
    outputData.setPatentYear(outputValue);
  }

  @RequiredArgsConstructor
  private static class PatentYearDataFormatValidator<D, E> implements DataFormatValidator<D, Integer, E> {

    private static final int MIN_PATENT_YEAR = 1812; // Год создания патентного права (LOL)


    @Override
    public <A extends AttributeParams<D, Integer, E, ?>> void validate(Integer patentYear,
                                                                       @Nonnull A attrProps,
                                                                       @Nonnull FormContext<D, E> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      if (patentYear == null) {
        throw new AttributeBadFormatException(attrProps.getName());
      }

      if (DateTimeUtilz.isSinceYear(patentYear, MIN_PATENT_YEAR)) {
        return;
      }

      throw new AttributeBadFormatException(attrProps.getName());
    }
  }
}
