package ru.rshb.intech.digital.statistics.agrobit.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

@Data
@Schema(name = "Данные об изменении параметра за месяц")
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class StatsMonthDto {

  @Schema(description = "Текстовая метка отсчета.")
  private String label;

  @Schema(description = "Номер отсчета (отсчет по оси X).")
  private Integer index;

  @Schema(description = "Значения (по оси Y).")
  private Double value;
}
