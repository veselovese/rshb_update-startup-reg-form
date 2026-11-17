package ru.rshb.intech.digital.puzzles.dto.farm;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Общая статистика использования по игре \"Цифровая ферма\"")
public record CommonUsageStatsDto(

    @Schema(description = "Средняя оценка пользователей.")
    Double avgMark,

    @Schema(description = "Кол-во игроков.")
    Integer activeUsersNum
) {

}
