package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Generated;

import java.util.List;

@Schema(name = "Справочники для формы регистрации участника \"Навстречу Агротеху\"")
@Generated
public record AgrotechOlympiadRegLookupsDto (

    @Schema(description = "Направления участия")
    List<LookupItemDto> directions,

    @Schema(description = "ВУЗы")
    List<LookupItemDto> university
) {
}
