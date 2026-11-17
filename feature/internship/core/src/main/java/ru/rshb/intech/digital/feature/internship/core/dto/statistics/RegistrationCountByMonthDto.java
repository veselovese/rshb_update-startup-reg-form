package ru.rshb.intech.digital.feature.internship.core.dto.statistics;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

@Schema(name = "Данные о количестве заявок на стажировку за месяц")
@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class RegistrationCountByMonthDto {

  @Schema(description = "Текстовая метка отсчета.")
  private String label;

  @Schema(description = "Номер отсчета (отсчет по оси X).")
  private Integer index;

  @Schema(description = "Значения (по оси Y).")
  private Double value;
}
