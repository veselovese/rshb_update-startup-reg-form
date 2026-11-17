package ru.rshb.intech.digital.content.provider.elasticsearch.mapper;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.content.model.Tool;
import ru.rshb.intech.digital.content.provider.api.ModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.ToolDto;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Component
public class ToolModelMapper implements ModelMapper<ToolDto, Tool> {

  @Nonnull
  @Override
  public Tool mapFromDto(@Nonnull ToolDto dto) {
    final var model = new Tool();
    model.setId(dto.getExternalId());
    model.setViews(defaultIfNull(dto.getViews(), ContentDefaults.VIEWS_NUM));
    model.setLikes(defaultIfNull(dto.getLikes(), ContentDefaults.LIKES_NUM));
    return model;
  }
}
