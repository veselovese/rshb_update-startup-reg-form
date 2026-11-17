package ru.rshb.intech.digital.puzzles.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Schema(name = "Кроссворд")
@Getter
@Setter
@Generated
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrosswordDto {

  @Schema(
      description = "Внешний ID кроссворда.",
      example = "8068a293-1b93-41d6-bdbd-2cf38d63ea9f"
  )
  private String id;

  @Schema(description = "Название кроссворда.")
  private String title;

  @Schema(description = "Описание кроссворда.")
  private String description;

  @Schema(description = "Категории кроссворда.")
  private List<CrosswordCategoryDto> categories;

  @Schema(description = "Вопросы кроссворда.")
  @JsonProperty("crossword")
  private List<CrosswordQuestionDto> questions;

  @Schema(description = "Количество участников.")
  private Long participantsCount;
}
