package ru.rshb.intech.digital.puzzles.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Schema(name = "Таблица рекордов для кроссворда")
@Getter
@Setter
@Builder
@Generated
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrosswordScoresTableDto {

  @Schema(
      description = "Внешний ID вопроса кроссворда.",
      example = "ef25de27-b397-422d-bd5b-ad24fa4ed4a0"
  )
  private String crosswordId;

  @Schema(
      description = "Название кроссворда",
      example = "Очень интересный кроссворд"
  )
  private String crosswordTitle;

  @Schema(description = "Категории кроссворда.")
  private List<CrosswordCategoryDto> crosswordCategories;

  @Schema(description = "Таблица рекордов кроссворда")
  private List<CrosswordScoresDto> crosswordScores;
}
