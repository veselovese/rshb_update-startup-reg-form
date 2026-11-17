package ru.rshb.intech.digital.feature.patents.core.service.publication.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.DataFormatValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeParams;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.feature.patents.core.dto.PatentPublicationDto;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.service.publication.form.PatentPublicationForm;
import ru.rshb.intech.digital.messages.I18n;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static ru.rshb.intech.digital.commons.validation.url.UrlUtils.isValidUrl;

/**
 * Атрибут "Сайт".
 * Форма заявки на размещение патента в витрине патентов.
 */
@Component("patentPubFormSiteUrl")
@FormAttribute(PatentPublicationForm.class)
public class SiteUrl extends TextAttributeBase<PatentPublicationDto, PatentPublication> { // TODO use UrlAttributeBase

  public static final String TITLE_KEY = "patents.contest.registration.form.attribute.site.url";
  private static final int MAX_LENGTH = 2048;

  public SiteUrl() {
    super(
        TextAttributeParams.Builder
            .<PatentPublicationDto, PatentPublication>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(true)
            .maxLength(MAX_LENGTH)
            .dataFormatValidator(new SiteUrlValidator<>())
            .build()
    );
  }

  @Override
  public String supplyInputValue(@Nonnull PatentPublicationDto inputData) {
    return inputData.getSiteUrl();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentPublication outputData) {
    outputData.setSiteUrl(outputValue);
  }

  private static class SiteUrlValidator<D, E> implements DataFormatValidator<D, String, E> {

    @Override
    public <A extends AttributeParams<D, String, E, ?>> void validate(String value,
                                                                      @Nonnull A attrProps,
                                                                      @Nonnull FormContext<D, E> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      if (isBlank(value) || isValidUrl(value.trim())) {
        return;
      }
      throw new AttributeBadFormatException(attrProps.getName());
    }
  }
}
