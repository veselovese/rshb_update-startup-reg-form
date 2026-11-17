package ru.rshb.intech.digital.puzzles.core.farm;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.puzzles.dto.farm.client.CommonUsageStatsClientDto;

/**
 * Клиент мини-игры "Цифровая ферма".
 */
public interface DigitalFarmGameClient {

  /**
   * Получить данные общей статистики об использовании игры.
   *
   * @return Общая статистика.
   */
  @Nonnull
  CommonUsageStatsClientDto getCommonUsageStats();

}
