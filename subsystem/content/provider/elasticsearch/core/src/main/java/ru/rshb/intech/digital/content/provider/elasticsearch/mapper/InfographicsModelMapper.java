package ru.rshb.intech.digital.content.provider.elasticsearch.mapper;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.content.model.Infographic;
import ru.rshb.intech.digital.content.provider.api.ModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.InfographicDto;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Component
public class InfographicsModelMapper implements ModelMapper<InfographicDto, Infographic> {

  @Nonnull
  @Override
  public Infographic mapFromDto(@Nonnull InfographicDto dto) {
    final var model = new Infographic();
    model.setId(dto.getExternalId());
    model.setViews(defaultIfNull(dto.getViews(), ContentDefaults.VIEWS_NUM));
    model.setLikes(defaultIfNull(dto.getLikes(), ContentDefaults.LIKES_NUM));
    model.setPublishDate(dto.getPublicationDate());
    model.setTitle(dto.getTitle());
    return model;
  }
}
