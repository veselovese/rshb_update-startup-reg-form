package ru.rshb.intech.digital.puzzles.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(name = "Предполагаемый ответ на вопрос")
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
public class Guess {

  @Schema(
      description = "Текст предполагаемого ответа на вопрос.",
      example = "42"
  )
  private String guess;
}
