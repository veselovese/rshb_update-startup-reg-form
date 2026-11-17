package ru.rshb.intech.digital.content.provider.elasticsearch.mapper;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.content.model.Trend;
import ru.rshb.intech.digital.content.provider.api.ModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.TrendDto;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Component
public class TrendModelMapper implements ModelMapper<TrendDto, Trend> {

  @Nonnull
  @Override
  public Trend mapFromDto(@Nonnull TrendDto dto) {
    final var model = new Trend();
    model.setId(dto.getExternalId());
    model.setViews(defaultIfNull(dto.getViews(), ContentDefaults.VIEWS_NUM));
    model.setLikes(defaultIfNull(dto.getLikes(), ContentDefaults.LIKES_NUM));
    model.setSlug(dto.getSlug());
    model.setPublishDate(dto.getPublicationDate());
    model.setImageUrl(dto.getImage());
    return model;
  }
}
