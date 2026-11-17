package ru.rshb.intech.digital.feature.internship.core.mapper;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFieldDto;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipField;

@Component
public class InternshipFieldMapperImpl implements InternshipFieldMapper {

  @Override
  public InternshipField fromDto(InternshipFieldDto dto) {

    if (dto == null) {
      return null;
    }

    final var entity = new InternshipField();
    entity.setKey(dto.getKey());
    entity.setTitle(dto.getTitle());
    return entity;
  }

  @Override
  public InternshipFieldDto fromEntity(InternshipField entity) {

    if (entity == null) {
      return null;
    }

    return new InternshipFieldDto(
        null,
        entity.getKey(),
        entity.getTitle(),
        null,
        null
    );
  }
}
