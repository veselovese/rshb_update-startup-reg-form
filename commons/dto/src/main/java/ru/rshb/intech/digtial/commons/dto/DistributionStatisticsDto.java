package ru.rshb.intech.digtial.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Schema(name = "Статистика с распределением")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Generated
public class DistributionStatisticsDto {

  @Schema(description = "Элементы распределения")
  private List<Item> items;

  @Schema(name = "Статистика. Параметр распределения")
  @Getter
  @Setter
  @EqualsAndHashCode
  @NoArgsConstructor
  @AllArgsConstructor
  @Generated
  public static class Item {

    @Schema(description = "Наименование метрики")
    private String title;

    @Schema(description = "Значение")
    private Double value;
  }
}
