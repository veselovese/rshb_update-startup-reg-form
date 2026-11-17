package ru.rshb.intech.digital.feature.startups.dto;

import java.io.File;

/**
 * Файл карточки стартапа/компании в файловом кэше.
 *
 * @param fileName Внешнее имя файла.
 * @param cachedFile Указатель на файл в кэше.
 */
public record StartupFileDto(

    String fileName,

    File cachedFile
) {
}
