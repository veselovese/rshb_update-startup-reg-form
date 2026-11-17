package ru.rshb.intech.digital.feature.patents.core.exception;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;

import static ru.rshb.intech.digital.messages.I18n.of;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PublicationValidationException {

  /**
   * Некорректное значение поля "Город".
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException unknownCityId() {
    return new AttributeBusinessRulesException(
        of("contest.patent.registration.validation.error.city-id.unknown")
    );
  }

  /**
   * Некорректное значение поля "Некооректное количество клиентов".
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException invalidClientsNumber() {
    return new AttributeBusinessRulesException(
        of("contest.patent.registration.validation.error.clients.num.unknown")
    );
  }

  /**
   * Некорректный тип коммерческой реализации.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException invalidCommercialImplementation() {
    return new AttributeBusinessRulesException(
        of("contest.patent.registration.validation.error.commercial.impl.unknown")
    );
  }

  /**
   * Некорректное значение раунда финансирования.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException invalidFinancingRound() {
    return new AttributeBusinessRulesException(
        of("contest.patent.registration.validation.error.investment.round.unknown")
    );
  }

  /**
   * Некорректный тип хозяйствующего субъекта.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException unknownManagingSubject() {
    return new AttributeBusinessRulesException(
        of("contest.patent.registration.validation.error.managing.subject.unknown")
    );
  }
}
