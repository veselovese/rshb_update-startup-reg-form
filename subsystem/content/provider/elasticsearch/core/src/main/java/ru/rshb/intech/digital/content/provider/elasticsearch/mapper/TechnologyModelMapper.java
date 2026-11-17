package ru.rshb.intech.digital.content.provider.elasticsearch.mapper;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.content.model.Technology;
import ru.rshb.intech.digital.content.provider.api.ModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.TechnologyDto;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Component
public class TechnologyModelMapper implements ModelMapper<TechnologyDto, Technology> {

  @Nonnull
  @Override
  public Technology mapFromDto(@Nonnull TechnologyDto dto) {
    final var model = new Technology();
    model.setId(dto.getExternalId());
    model.setViews(defaultIfNull(dto.getViews(), ContentDefaults.VIEWS_NUM));
    model.setLikes(defaultIfNull(dto.getLikes(), ContentDefaults.LIKES_NUM));
    model.setTitle(dto.getTitle());
    model.setSlug(dto.getSlug());
    return model;
  }
}
