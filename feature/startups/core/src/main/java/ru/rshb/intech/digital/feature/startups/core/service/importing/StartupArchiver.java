package ru.rshb.intech.digital.feature.startups.core.service.importing;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.startups.dto.StartupArchive;
import ru.rshb.intech.digital.feature.startups.dto.StartupFileDto;

/**
 * Архиватор карточек стартапов/компаний.
 */
public interface StartupArchiver {

  /**
   * Добавляет файл карточки стартапа/компании в архив.
   *
   * @param startupFile файл карточки с данными стартапа/компании.
   */
  void add(@Nonnull StartupFileDto startupFile);

  /**
   * Выполняет сжатие и вовзвращает указатель на файл архива в кэше.
   *
   * @param outputFileName Внешнее имя создаваемого архива.
   * @return Файл архива в кэше.
   */
  @Nonnull
  StartupArchive compress(@Nonnull String outputFileName);

}
