package ru.rshb.intech.digital.puzzles.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rshb.intech.digital.content.model.Crossword.Question.Direction;

@Schema(name = "Вопрос кроссворда")
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrosswordQuestionDto {

  @Schema(
      description = "Внешний ID вопроса кроссворда.",
      example = "ef25de27-b397-422d-bd5b-ad24fa4ed4a0"
  )
  private String id;

  @Schema(
      description = "Текст вопроса.",
      example = "Что делать?"
  )
  private String title;

  @Schema(
      description = "Позиция первой буквы слова на поле кроссворда в формате [колонка, строка]",
      example = "[0, 0]"
  )
  private long[] start;

  @Schema(
      description = "Количество букв в ответе.",
      example = "10"
  )
  private long count;

  @Schema(
      description = "Ориентация слова (по вертикали или по горизонтали).",
      example = "row"
  )
  private Direction direction;

  private String answer;
}
