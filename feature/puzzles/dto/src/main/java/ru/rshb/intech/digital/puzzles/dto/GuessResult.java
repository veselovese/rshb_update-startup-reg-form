package ru.rshb.intech.digital.puzzles.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Generated;

@Schema(name = "Результат проверки ответа на вопрос")
@Generated
public record GuessResult(

    @Schema(
        description = "true - если вариант ответа правильный.",
        example = "false"
    )
    Boolean right
) {

}
