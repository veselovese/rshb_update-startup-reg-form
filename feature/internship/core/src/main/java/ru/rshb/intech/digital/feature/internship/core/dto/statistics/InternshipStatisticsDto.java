package ru.rshb.intech.digital.feature.internship.core.dto.statistics;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Generated;

import java.util.List;

@Schema(name = "Данные статистики по стажировкам")
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public record InternshipStatisticsDto(

    @Schema(description = "Графики")
    List<RegistrationCountDto> series

) {
}
