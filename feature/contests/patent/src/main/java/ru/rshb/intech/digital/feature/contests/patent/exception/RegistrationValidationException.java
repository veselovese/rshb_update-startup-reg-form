package ru.rshb.intech.digital.feature.contests.patent.exception;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;

import static ru.rshb.intech.digital.messages.I18n.of;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationValidationException {

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
   * Некооректное количество клиентов.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException unknownClientsNumber() {
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
  public static AttributeBusinessRulesException unknownCommercialImplementation() {
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
  public static AttributeBusinessRulesException unknownFinancingRound() {
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

  /**
   * Некорректное значение этапа проекта.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException unknownProjectStage() {
    return new AttributeBusinessRulesException(
        of("contest.patent.registration.validation.error.project.stage.unknown")
    );
  }

}
