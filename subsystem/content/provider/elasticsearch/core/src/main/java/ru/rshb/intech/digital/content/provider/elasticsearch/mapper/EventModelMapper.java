package ru.rshb.intech.digital.content.provider.elasticsearch.mapper;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.content.model.Event;
import ru.rshb.intech.digital.content.provider.api.ModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.EventDto;

@Component
public class EventModelMapper implements ModelMapper<EventDto, Event> {

  @Nonnull
  @Override
  public Event mapFromDto(@Nonnull EventDto dto) {
    final var model = new Event();
    model.setId(dto.getExternalId());
    model.setViews(dto.getViews());
    model.setLikes(dto.getLikes());
    model.setTitle(dto.getTitle());
    model.setSummary(dto.getSummary());
    model.setUrl(dto.getUrl());
    model.setSlug(dto.getSlug());
    model.setCity(dto.getCity());
    model.setStartDate(dto.getStartDate());
    model.setEndDate(dto.getEndDate());
    return model;
  }
}
