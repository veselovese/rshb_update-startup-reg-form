package ru.rshb.intech.digital.references.cities.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Schema(name = "Населенный пункт")
@Data
@Generated
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityDto {

  @Schema(
      description = "ID записи.",
      example = "f29a76c4-1507-42e4-ae7f-d8d55e4ed474"
  )
  private String id;

  @Schema(
      description = "Наименование населенного пункта.",
      example = "г Сызрань"
  )
  private String title;

  @Schema(
      description = "Регион.",
      example = "Самарская область"
  )
  private String region;
}
