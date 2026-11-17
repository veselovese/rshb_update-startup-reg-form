package ru.rshb.intech.digital.feature.internship.core.mapper;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.internship.core.dto.WorkHoursDto;
import ru.rshb.intech.digital.feature.internship.core.entity.WorkHours;

@Component
public class WorkHoursMapperImpl implements WorkHoursMapper {

  @Override
  public WorkHours fromDto(WorkHoursDto dto) {

    if (dto == null) {
      return null;
    }

    final var model = new WorkHours();
    model.setExternalId(dto.getId());
    model.setTitle(dto.getTitle());
    return model;
  }

  @Override
  public WorkHoursDto fromEntity(WorkHours entity) {

    if (entity == null) {
      return null;
    }

    final var dto = new WorkHoursDto();
    dto.setId(entity.getExternalId());
    dto.setTitle(entity.getTitle());
    return dto;
  }
}
