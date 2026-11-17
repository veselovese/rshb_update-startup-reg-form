package ru.rshb.intech.digital.content.provider.elasticsearch.mapper;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.content.model.Innovation;
import ru.rshb.intech.digital.content.provider.api.ModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.InnovationDto;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Component
public class InnovationModelMapper implements ModelMapper<InnovationDto, Innovation> {

  @Nonnull
  @Override
  public Innovation mapFromDto(@Nonnull InnovationDto dto) {
    final var model = new Innovation();
    model.setId(dto.getExternalId());
    model.setViews(defaultIfNull(dto.getViews(), ContentDefaults.VIEWS_NUM));
    model.setLikes(defaultIfNull(dto.getLikes(), ContentDefaults.LIKES_NUM));
    model.setTitle(dto.getTitle());
    model.setUrl(dto.getUrl());
    return model;
  }
}

