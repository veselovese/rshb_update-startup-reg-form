package ru.rshb.intech.digital.feature.startups.dto;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotNull;
import lombok.Generated;
import org.springframework.web.multipart.MultipartFile;

/**
 * DTO для формы загрузки данных карторчек стартапов/компаний.
 *
 * @param startupsFile Файл в формате XLSX
 */
@Hidden
@Generated
public record StartupConvertDto(

    @NotNull(message = "Не указан файл")
    MultipartFile startupsFile
) {
}
