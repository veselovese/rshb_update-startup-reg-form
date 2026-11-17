package ru.rshb.intech.digital.commons.forms.attributes.applicant;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeParams;
import ru.rshb.intech.digital.messages.I18n;

/**
 * Частичная реализация обработчика атрибута "ФИО".
 *
 * @param <D> Тип входных данных (DTO).
 * @param <E> Выходной тип данных (Entity).
 */
public abstract class ApplicantNameAttributeBase<D, E> extends TextAttributeBase<D, E> {

  public static final String DEFAULT_TITLE_KEY = "common.form.attribute.fio.title";
  public static final int MAX_LENGTH_DEFAULT = 255;


  public ApplicantNameAttributeBase() {
    this(false);
  }

  public ApplicantNameAttributeBase(@Nonnull TextAttributeParams<D, E> params) {
    super(params);
  }

  /**
   * Конструктор обработчика атрибута "ФИО".
   *
   * @param nullable если <code>true</code>, то <code>null</code> - допустимое входное значение.
   */
  public ApplicantNameAttributeBase(boolean nullable) {
    this(DEFAULT_TITLE_KEY, nullable, MAX_LENGTH_DEFAULT);
  }

  /**
   * Конструктор обработчика атрибута "ФИО".
   *
   * @param maxLength Макс. допустимое кол-во символов.
   */
  public ApplicantNameAttributeBase(int maxLength) {
    this(DEFAULT_TITLE_KEY, false, maxLength);
  }

  /**
   * Конструктор обработчика атрибута "ФИО".
   *
   * @param titleKey ID наименования атрибута (i18n).
   * @param maxLength Макс. допустимое кол-во символов.
   */
  public ApplicantNameAttributeBase(@Nonnull String titleKey, int maxLength) {
    this(titleKey, false, maxLength);
  }

  /**
   * Конструктор обработчика атрибута "ФИО".
   *
   * @param nullable если <code>true</code>, то <code>null</code> - допустимое входное значение.
   * @param maxLength Макс. допустимое кол-во символов.
   */
  public ApplicantNameAttributeBase(boolean nullable, int maxLength) {
    this(DEFAULT_TITLE_KEY, nullable, maxLength);
  }

  /**
   * Конструктор обработчика атрибута "ФИО".
   *
   * @param titleKey ID наименования атрибута (i18n).
   * @param nullable если <code>true</code>, то <code>null</code> - допустимое входное значение.
   * @param maxLength Макс. допустимое кол-во символов.
   */
  public ApplicantNameAttributeBase(@Nonnull String titleKey, boolean nullable, int maxLength) {
    super(
        TextAttributeParams.Builder
            .<D, E>create()
            .name(I18n.of(titleKey))
            .nullable(nullable)
            .maxLength(maxLength)
            .build()
    );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
