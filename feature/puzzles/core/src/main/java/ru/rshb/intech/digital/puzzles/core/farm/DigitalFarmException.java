package ru.rshb.intech.digital.puzzles.core.farm;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DigitalFarmException {

  /**
   * Бэкенд игры "Цифровая ферма" вернул пустое тело в ответе на запрос.
   *
   * @return Исключение.
   */
  @Nonnull
  public static RuntimeException emptyBodyReceived() {
    return new RuntimeException("Digital Farm backend returned empty body");
  }

  /**
   * Ошибка при попытке получения данных от бэкенда игры "Цифровая ферма".
   *
   * @return Исключение.
   */
  @Nonnull
  public static RuntimeException cantReceiveData(@Nonnull Throwable throwable) {
    return new RuntimeException("Digital Farm game data retrieve error", throwable);
  }
}
