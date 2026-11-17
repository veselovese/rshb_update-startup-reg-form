package ru.rshb.intech.digital.feature.startups.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Generated;

@Schema(name = "Значение из справочника")
@Generated
public record LookupValueDto(

    @Schema(description = "Идентификатор значения справочника")
    String id,

    @Schema(description = "Значение справочника")
    String value
) {

}
