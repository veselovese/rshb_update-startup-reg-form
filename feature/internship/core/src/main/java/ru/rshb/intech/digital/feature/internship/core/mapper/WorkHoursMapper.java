package ru.rshb.intech.digital.feature.internship.core.mapper;

import ru.rshb.intech.digital.feature.internship.core.dto.WorkHoursDto;
import ru.rshb.intech.digital.feature.internship.core.entity.WorkHours;

import java.util.List;

public interface WorkHoursMapper {

  WorkHours fromDto(WorkHoursDto dto);

  default List<WorkHours> fromDtos(List<WorkHoursDto> dtos) {
    return (dtos != null)
        ? dtos.stream()
        .map(this::fromDto)
        .toList()
        : null;
  }

  WorkHoursDto fromEntity(WorkHours entity);

  default List<WorkHoursDto> fromEntities(List<WorkHours> entities) {
    return (entities != null)
        ? entities.stream()
        .map(this::fromEntity)
        .toList()
        : null;
  }

}
