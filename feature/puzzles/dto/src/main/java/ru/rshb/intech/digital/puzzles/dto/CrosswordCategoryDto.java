package ru.rshb.intech.digital.puzzles.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(name = "Категория кроссворда")
@Setter
@Getter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CrosswordCategoryDto {

  @Schema(
      description = "Внешний ID категории кроссворда",
      example = "bdadf6b2-3722-4b2f-943d-8cf10b80b9b7"
  )
  private String id;

  @Schema(
      description = "Наименование категории просмотра. Значение из справочника.",
      example = "Дизайн"
  )
  private String title;
}
