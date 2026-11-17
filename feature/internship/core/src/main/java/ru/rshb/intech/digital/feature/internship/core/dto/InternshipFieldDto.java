package ru.rshb.intech.digital.feature.internship.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Generated;
import lombok.Value;

@Schema(name = "Направление стажировки")
@Value
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InternshipFieldDto {

    Long id;
    String key;
    String title;
    Boolean enabled;
    Boolean deleted;
}
