package ru.rshb.intech.digtial.commons.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Generated;

@Schema(
    enumAsRef = true,
    name = "Статус выполнения операции",
    example = "SUCCESS|ERROR"
)
@Generated
public enum ResultStatus {

  SUCCESS,

  ERROR
}
