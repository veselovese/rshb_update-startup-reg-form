package ru.rshb.intech.digital.puzzles.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Schema(name = "Результат проверки ответов на вопросы кроссворда")
@Getter
@Setter
@Builder
@Generated
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CrosswordCheckResult {

  @Schema(
      description = "ID кроссворда",
      example = "774c53fe-f1db-469b-a529-5d006e0107c9"
  )
  private String crosswordId;

  @Schema(
      description =
          """
          Признак того, что кроссворд разгадан (т.е. в случае, когда предоставлен полный набор ответов
          на все вопросы кроссворда и каждый из этих ответов - верный)
          """
  )
  private Boolean complete;

  @Schema(
      description = "ID вопросов, на которые даны неверные ответы",
      example = "абракадабра"
  )
  private List<String> wrong;
}
