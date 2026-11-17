package ru.rshb.intech.digital.statistics.agrobit.salary.dto.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(name = "Справочник категорий и наименований профессий")
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalaryPositionCategoryLookupDto {

  @Schema(description = "Категории профессий")
  @JsonProperty("categories")
  private List<SalaryPositionCategoryDto> categories;
}
