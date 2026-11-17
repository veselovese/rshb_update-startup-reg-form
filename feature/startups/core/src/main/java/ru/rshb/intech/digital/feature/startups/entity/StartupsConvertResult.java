package ru.rshb.intech.digital.feature.startups.entity;

/**
 * Результат коневертирования данных о стартапах/компаниях.
 *
 * @param fileName Наименование выходного файла.
 * @param fileData Данные файла.
 */
public record StartupsConvertResult(

    String fileName,

    byte[] fileData
) {
}
