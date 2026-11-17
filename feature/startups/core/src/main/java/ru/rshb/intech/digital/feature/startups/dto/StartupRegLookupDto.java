package ru.rshb.intech.digital.feature.startups.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Generated;

import java.util.List;

@Schema(name = "Справочники для формы заявки на размещение стартапа")
@Generated
public record StartupRegLookupDto (

    @Schema(description = "Категории стартапа")
    List<LookupValueDto> categories,

    @Schema(description = "Стадии проекта")
    List<LookupValueDto> projectStage,

    @Schema(description = "Варианты количества клиентов")
    List<LookupValueDto> clients,

    @Schema(description = "Раунды финансирования")
    List<LookupValueDto> financing,

    @Schema(description = "Виды валюты")
    List<LookupValueDto> currency,

    @Schema(description = "Список стран для первичного отображения")
    List<LookupValueDto> country
) {
}
