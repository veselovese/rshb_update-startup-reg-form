package ru.rshb.intech.digital.feature.internship.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Schema(name = "Данные справочника о рабочих часах")
@Data
@Generated
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkHoursDto {

  @Schema(
      description = "ID элемента",
      example = "102fab07-2db3-4fec-9b35-7bb4ba6fe5be"
  )
  private String id;

  @Schema(
      description = "Наименование ступени",
      example = "бакалавриат"
  )
  @JsonProperty("value")
  private String title;

  public WorkHoursDto(@Nonnull String id, @Nonnull String title) {
    setId(id);
    setTitle(title);
  }
}
