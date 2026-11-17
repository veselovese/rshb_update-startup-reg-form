package ru.rshb.intech.digital.statistics.agrobit.tourism.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Generated;

@Data
@Schema(name = "Вид туристического отдыха.")
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecreationTypeDto {

  @Schema(description = "Внешний идентификатор вида отдыха.")
  private String id;

  @Schema(description = "Наименование вида отдыха.")
  private String title;

}
