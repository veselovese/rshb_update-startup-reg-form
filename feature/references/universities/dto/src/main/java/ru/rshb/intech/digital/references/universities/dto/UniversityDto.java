package ru.rshb.intech.digital.references.universities.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Schema(name = "Учебное заведение")
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UniversityDto {

  @Schema(
      description = "ID записи.",
      example = "f29a76c4-1507-42e4-ae7f-d8d55e4ed474"
  )
  private String id;

  @Schema(
      description = "Наименование учебного заведения.",
      example = "Московский Государственный Университет"
  )
  private String fullName;

  @Schema(
      description = "Сокращенное обозначение ученбного заведения",
      example = "МГУ"
  )
  private String shortName;
}
