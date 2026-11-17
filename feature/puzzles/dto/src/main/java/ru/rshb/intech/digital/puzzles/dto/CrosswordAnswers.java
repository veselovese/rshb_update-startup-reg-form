package ru.rshb.intech.digital.puzzles.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Schema(name = "Ответы на вопросы кроссворда")
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CrosswordAnswers {

  @Schema(description = "Ответы на вопросы кроссворда")
  private List<Answer> questions;
}
