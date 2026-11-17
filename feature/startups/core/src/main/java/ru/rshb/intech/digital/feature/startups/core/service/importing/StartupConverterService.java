package ru.rshb.intech.digital.feature.startups.core.service.importing;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.startups.dto.StartupConvertDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupsConvertResult;

/**
 * Сервис конвертера карточек стартапов/компаний
 */
public interface StartupConverterService {

  /**
   * Преобразует предоставленный, специальным образом отформатированный файл в формате Excel (XLSX) в
   * архив из JSON-файлов (одна строка - один файл).
   *
   * @param sessionId ID HTTP сессии.
   * @param convertDto Входные данные.
   * @return Архив с JSON-файлами.
   */
  @Nonnull
  StartupsConvertResult convert(@Nonnull String sessionId, @Nonnull StartupConvertDto convertDto);
}
