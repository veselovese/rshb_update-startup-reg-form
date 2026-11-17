package ru.rshb.intech.digital.feature.blogs.core.experts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Schema(name = "Экспертная область")
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class ExpertFieldDto {

  @Schema(
      description = "Внешний ID значения справочника",
      example = "28d18890-c88c-4cee-a32b-e7680dd6eb57"
  )
  private String id;

  @Schema(
      description = "Наименование экспертной области.",
      example = "Сельское хозяйство"
  )
  private String value;

}
