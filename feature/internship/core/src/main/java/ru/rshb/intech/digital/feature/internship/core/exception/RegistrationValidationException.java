package ru.rshb.intech.digital.feature.internship.core.exception;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.Generated;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.commons.utils.exceptiion.DigitalServiceException;

import static ru.rshb.intech.digital.messages.I18n.of;

@Generated
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationValidationException extends DigitalServiceException {

  /**
   * Некорректное значение даты рождения.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException invalidBirthDate() {
    return new AttributeBusinessRulesException(
        of("internship.registration.error.validation.birthdate.value")
    );
  }

  /**
   * Некорректный ID города.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException invalidCityId() {
    return new AttributeBusinessRulesException(
        of("internship.registration.error.validation.city.id.format")
    );
  }

  /**
   * Некорректное значение года обучения.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException invalidEducationYear() {
    return new AttributeBusinessRulesException(
        of("internship.registration.error.validation.education.year")
    );
  }

  /**
   * Некорректное значение уровня образования.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException invalidEducationLevel() {
    return new AttributeBusinessRulesException(
        of("internship.registration.error.validation.education.stage")
    );
  }

  /**
   * Некорректное направление стажировки.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException invalidInternshipDirection() {
    return new AttributeBusinessRulesException(
        of("internship.registration.error.validation.internship.field.unknown")
    );
  }

  /**
   * Заявка с таким номером телефона уже существует.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException applicationWithPhoneAlreadyExist() {
    return new AttributeBusinessRulesException(
        of("common.error.validation.phone.already.exist")
    );
  }

  /**
   * Некорректный ID ВУЗа.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException invalidUniversityId() {
    return new AttributeBusinessRulesException(
        of("internship.registration.error.validation.university.id.format")
    );
  }

  /**
   * Некорректное значение количества рабочих часов.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException invalidWorkHours() {
    return new AttributeBusinessRulesException(
        of("internship.registration.error.validation.pref-work-hours.incorrect")
    );
  }

  /**
   * Некорректный формат поля "Год обучения".
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBadFormatException invalidEducationYearFormat() {
    return new AttributeBadFormatException(
        of("internship.registration.form.field.education.year")
    );
  }
}
