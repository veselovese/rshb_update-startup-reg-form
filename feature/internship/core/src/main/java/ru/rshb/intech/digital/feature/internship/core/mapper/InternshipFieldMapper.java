package ru.rshb.intech.digital.feature.internship.core.mapper;

import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFieldDto;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipField;

import java.util.List;

public interface InternshipFieldMapper {

  InternshipField fromDto(InternshipFieldDto dto);

  default List<InternshipField> fromDtos(List<InternshipFieldDto> dtos) {
    return (dtos != null)
        ? dtos.stream()
        .map(this::fromDto)
        .toList()
        : null;
  }

  InternshipFieldDto fromEntity(InternshipField entity);

  default List<InternshipFieldDto> fromEntities(List<InternshipField> entities) {
    return (entities != null)
        ? entities.stream()
        .map(this::fromEntity)
        .toList()
        : null;
  }
}
