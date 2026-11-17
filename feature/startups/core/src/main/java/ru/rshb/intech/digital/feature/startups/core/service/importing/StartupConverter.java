package ru.rshb.intech.digital.feature.startups.core.service.importing;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.startups.dto.StartupFileDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupRecord;

/**
 * Конвертер данных стартапов/компаний.
 */
public interface StartupConverter {

  /**
   * Конвертирует данные о стартапах/компаниях.
   *
   * @param startupRecord Исходные данные.
   * @return Конвертированные данные.
   */
  @Nonnull
  StartupFileDto convert(@Nonnull StartupRecord startupRecord);
}
