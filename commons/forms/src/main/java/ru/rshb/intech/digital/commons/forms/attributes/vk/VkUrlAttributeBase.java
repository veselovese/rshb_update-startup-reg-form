package ru.rshb.intech.digital.commons.forms.attributes.vk;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.DataFormatValidator;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.commons.validation.vk.VkUrlUtils;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static ru.rshb.intech.digital.commons.validation.vk.VkUrlUtils.MAX_LENGTH;

/**
 * Частичная реализация атрибута "Ссылка на профиль VK".
 *
 * @param <D> Тип входных данных (DTO).
 * @param <E> Выходной тип данных (Entity).
 */
public abstract class VkUrlAttributeBase<D, E> extends TextAttributeBase<D, E> {

  public static final String TITLE_KEY = "common.form.attribute.vk.url.title";


  public VkUrlAttributeBase() {
    this(true);
  }

  public VkUrlAttributeBase(boolean nullable) {
    super(TITLE_KEY, nullable, MAX_LENGTH, new VkUrlDataFormatValidator<>());
  }


  private static class VkUrlDataFormatValidator<D, E> implements DataFormatValidator<D, String, E> {

    @Override
    public <A extends AttributeParams<D, String, E, ?>> void validate(String value,
                                                                      @Nonnull A attrProps,
                                                                      @Nonnull FormContext<D, E> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      if (isBlank(value) || VkUrlUtils.isValidVkUrl(value)) {
        return;
      }

      throw new AttributeBadFormatException(attrProps.getName());
    }
  }
}
