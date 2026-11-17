package ru.rshb.intech.digital.feature.blogs.core.experts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(name = "Справочники формы \"Стать экспертом\"")
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExpertRegistrationRefDto {

  @Schema(description = "Справочник экспертных областей.")
  private List<ExpertFieldDto> expertDirection;
}
