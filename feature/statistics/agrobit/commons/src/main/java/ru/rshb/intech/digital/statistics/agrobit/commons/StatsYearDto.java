package ru.rshb.intech.digital.statistics.agrobit.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Schema(name = "Данные за год по месяцам")
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatsYearDto {

  @Schema(description = "Год")
  private int year;

  @Schema(description = "Данные по месяцам")
  private List<StatsMonthDto> months;
}
