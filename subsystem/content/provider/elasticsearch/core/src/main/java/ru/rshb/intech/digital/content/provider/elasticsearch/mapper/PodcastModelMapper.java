package ru.rshb.intech.digital.content.provider.elasticsearch.mapper;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.content.model.Podcast;
import ru.rshb.intech.digital.content.provider.api.ModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.PodcastDto;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Component
public class PodcastModelMapper implements ModelMapper<PodcastDto, Podcast> {

  @Nonnull
  @Override
  public Podcast mapFromDto(@Nonnull PodcastDto dto) {
    final var model = new Podcast();
    model.setId(dto.getExternalId());
    model.setViews(defaultIfNull(dto.getViews(), ContentDefaults.VIEWS_NUM));
    model.setLikes(defaultIfNull(dto.getLikes(), ContentDefaults.LIKES_NUM));
    model.setTitle(dto.getTitle());
    model.setLabel(dto.getLabel());
    model.setSlug(dto.getSlug());
    model.setDescription(dto.getDescription());
    model.setPublishDate(dto.getPublicationDate());
    model.setChannel(dto.getChannel());
    return model;
  }
}
