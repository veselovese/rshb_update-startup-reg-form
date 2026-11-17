package ru.rshb.intech.digital.content.provider.elasticsearch.mapper;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.content.model.Lecture;
import ru.rshb.intech.digital.content.provider.api.ModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.LectureDto;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Component
public class LecutreModelMapper implements ModelMapper<LectureDto, Lecture> {

  @Nonnull
  @Override
  public Lecture mapFromDto(@Nonnull LectureDto dto) {
    final var model = new Lecture();
    model.setId(dto.getExternalId());
    model.setTitle(dto.getTitle());
    model.setViews(defaultIfNull(dto.getViews(), ContentDefaults.VIEWS_NUM));
    model.setLikes(defaultIfNull(dto.getLikes(), ContentDefaults.LIKES_NUM));
    model.setSlug(dto.getSlug());
    model.setImage(dto.getImage());
    model.setLabels(dto.getLabels());
    return model;
  }
}
