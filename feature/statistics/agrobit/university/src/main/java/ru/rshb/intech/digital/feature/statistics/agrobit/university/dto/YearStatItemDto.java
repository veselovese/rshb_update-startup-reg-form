package ru.rshb.intech.digital.feature.statistics.agrobit.university.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@Schema(name = "Агробит. ВУЗы. Данные статистики")
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class YearStatItemDto {

  private String year;
  private Long value;
}
