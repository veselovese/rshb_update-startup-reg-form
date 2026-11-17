package ru.rshb.intech.digital.statistics.agrobit.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
@Schema(name = "Данные параметра по годам")
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatsByYearsDto {

  @Schema(description = "Наименование параметра, отображаемого на графике")
  private String name;

  @Schema(description = "Данные по годам")
  private List<StatsYearDto> years;
}
