package ru.rshb.intech.digital.statistics.agrobit.salary.dto.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Generated;

@Schema(name = "Профессия")
@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalaryPositionDto {

  @Schema(description = "Внешний идентификатор")
  @JsonProperty(value = "id", index = 0)
  private String externalId;

  @Schema(description = "Наименование профессии")
  private String title;

}
