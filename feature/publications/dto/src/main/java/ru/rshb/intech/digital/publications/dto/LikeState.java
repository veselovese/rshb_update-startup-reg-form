package ru.rshb.intech.digital.publications.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

@Schema(name = "Состояние лайка")
@Generated
@RequiredArgsConstructor
public enum LikeState {

    LIKED("liked"),

    UNLIKED("unliked");

    private final String key;

    @JsonValue
    public String getKey() {
        return key;
    }
}
