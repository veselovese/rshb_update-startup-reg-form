package ru.rshb.intech.digital.feature.contests.patent.service.registration.form.attribute;

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
import ru.rshb.intech.digital.feature.contests.patent.dto.PatentContestRegistrationRequestDto;
import ru.rshb.intech.digital.feature.contests.patent.entity.PatentContestRegistration;
import ru.rshb.intech.digital.feature.contests.patent.service.registration.form.PatentContestRegistrationForm;
import ru.rshb.intech.digital.messages.I18n;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static ru.rshb.intech.digital.commons.validation.url.UrlUtils.isValidUrl;

/**
 * Атрибут "Сайт".
 * Форма регистрации участника в конкурсе патентов.
 */
@Component
@FormAttribute(PatentContestRegistrationForm.class)
public class SiteUrl extends TextAttributeBase<PatentContestRegistrationRequestDto, PatentContestRegistration> { // TODO make base URL attribute

  private static final String TITLE_KEY = "patents.contest.registration.form.attribute.site.url";
  private static final int MAX_LENGTH = 2048;

  public SiteUrl() {
    super(
        TextAttributeParams.Builder
            .<PatentContestRegistrationRequestDto, PatentContestRegistration>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(true)
            .maxLength(MAX_LENGTH)
            .dataFormatValidator(new SiteUrlValidator<>())
            .build()
    );
  }

  @Override
  public String supplyInputValue(@Nonnull PatentContestRegistrationRequestDto inputData) {
    return inputData.getSiteUrl();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentContestRegistration outputData) {
    outputData.setSiteUrl(outputValue);
  }

  private static class SiteUrlValidator<D, E> implements DataFormatValidator<D, String, E> {

    @Override
    public <A extends AttributeParams<D, String, E, ?>> void validate(String value,
                                                                      @Nonnull A attrProps,
                                                                      @Nonnull FormContext<D, E> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      if (isBlank(value)) {
        return;
      }

      if (isValidUrl(value.trim())) {
        return;
      }

      throw new AttributeBadFormatException(attrProps.getName());
    }
  }
}
