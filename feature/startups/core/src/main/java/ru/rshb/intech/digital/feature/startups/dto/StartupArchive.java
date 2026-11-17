package ru.rshb.intech.digital.feature.startups.dto;

import java.io.File;

/**
 * Файл архива в кэше.
 *
 * @param fileName Внешнее наименование файла.
 * @param cachedFile Указатель на файл в файловом кэше.
 */
public record StartupArchive (

    String fileName,

    File cachedFile
) {

}
