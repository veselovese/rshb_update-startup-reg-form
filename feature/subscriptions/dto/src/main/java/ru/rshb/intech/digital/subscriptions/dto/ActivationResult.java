package ru.rshb.intech.digital.subscriptions.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Результат активации подписки")
public record ActivationResult(

    @Schema(
        description = "Статус активации подписки",
        example = "SUBSCRIBED"
    )
    ActivationStatus status
) {

}
