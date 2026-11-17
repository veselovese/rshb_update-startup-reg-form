package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Generated;

@Schema(name = "Значение справочника")
@Generated
public record LookupItemDto (

    String id,

    String value
) {

}
