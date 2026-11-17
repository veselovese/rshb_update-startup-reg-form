package ru.rshb.intech.digital.feature.patents.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.feature.contests.patent.dto.CityDto;
import ru.rshb.intech.digital.feature.contests.patent.dto.ClientNumberDto;
import ru.rshb.intech.digital.feature.contests.patent.dto.CommercialImplementationDto;
import ru.rshb.intech.digital.feature.contests.patent.dto.FinancingRoundDto;
import ru.rshb.intech.digital.feature.contests.patent.dto.ManagingSubjectDto;
import ru.rshb.intech.digital.feature.contests.patent.dto.ProjectStageDto;

import java.util.List;

@Schema(name = "Справочники для заявки на добавление патента в витрину.")
@Data
@Builder
@Generated
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatentPublicationLookupDto {

  @Schema(description = "Типы хозяйствующих субъектов")
  private List<ManagingSubjectDto> managingSubjects;

  @Schema(description = "Раунды финансирования")
  private List<FinancingRoundDto> financingRounds;

  @Schema(description = "Размеры клиентской базы")
  private List<ClientNumberDto> clientNumberVariants;

  @Schema(description = "Города по-умолчанию")
  private List<CityDto> cities;

  @Schema(description = "Стадии проекта")
  private List<ProjectStageDto> projectStages;

  @Schema(description = "Типы коммерческой реализации")
  private List<CommercialImplementationDto> commercialImpls;
}
