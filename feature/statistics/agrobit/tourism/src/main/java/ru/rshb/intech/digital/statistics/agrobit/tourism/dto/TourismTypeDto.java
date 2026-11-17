package ru.rshb.intech.digital.statistics.agrobit.tourism.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
@Schema(name = "Тип туризма")
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TourismTypeDto {

  @Schema(description = "Внешний идентификатор типа туризма.")
  private String id;

  @Schema(description = "Наименование типа туризма.")
  private String title;

  @Schema(description = "Виды отдыха данного типа туризма.")
  private List<RecreationTypeDto> recreationTypes;
}
