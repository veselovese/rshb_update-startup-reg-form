package ru.rshb.intech.digital.puzzles.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(name = "Ответ на вопрос")
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Answer {

  @Schema(
      description = "ID вопроса",
      example = "d1a1bbf6-de42-4a33-8b18-0badc8a5fb64"
  )
  private String questionId;


  @Schema(
      description = "Текст ответа на вопрос.",
      example = "42"
  )
  private String answer;


  public Answer(@Nonnull String answer) {
    this.answer = answer;
  }
}
