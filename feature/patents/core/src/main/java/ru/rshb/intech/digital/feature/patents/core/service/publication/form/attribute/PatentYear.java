package ru.rshb.intech.digital.feature.patents.core.service.publication.form.attribute;

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
import ru.rshb.intech.digital.feature.patents.core.dto.PatentPublicationDto;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.service.publication.form.PatentPublicationForm;
import ru.rshb.intech.digital.messages.I18n;

/**
 * Атрибут "Год патентирования".
 * Форма заявки на размещение патента в витрине патентов.
 */
@Component("patentPubFormPatentYear")
@FormAttribute(PatentPublicationForm.class)
public class PatentYear extends Attribute<PatentPublicationDto, Integer, PatentPublication, Integer> {

  public static final String TITLE_KEY = "patents.contest.registration.form.attribute.patent.year";

  public PatentYear() {
    super(
        AttributeParams.Builder
            .<PatentPublicationDto, Integer, PatentPublication, Integer>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(false)
            .dataFormatValidator(new PatentYearDataFormatValidator<>())
            .valueMapper(new IntegerAttributeBase.ThroughValueMapper())
            .build()
    );
  }

  @Override
  public Integer supplyInputValue(@Nonnull PatentPublicationDto inputData) {
    return inputData.getPatentYear();
  }

  @Override
  public void consumeOutputValue(Integer outputValue, @Nonnull PatentPublication outputData) {
    outputData.setPatentYear(outputValue);
  }

  @RequiredArgsConstructor
  private static class PatentYearDataFormatValidator<D, E> implements DataFormatValidator<D, Integer, E> {

    private static final int MIN_YEAR = 1812; // Год создания патентного права (LOL).


    @Override
    public <A extends AttributeParams<D, Integer, E, ?>> void validate(Integer patentYear,
                                                                       @Nonnull A attrProps,
                                                                       @Nonnull FormContext<D, E> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      if (patentYear == null) {
        throw new AttributeBadFormatException(attrProps.getName());
      }

      if (!DateTimeUtilz.isSinceYear(patentYear, MIN_YEAR)) {
        throw new AttributeBadFormatException(attrProps.getName());
      }
    }
  }
}
