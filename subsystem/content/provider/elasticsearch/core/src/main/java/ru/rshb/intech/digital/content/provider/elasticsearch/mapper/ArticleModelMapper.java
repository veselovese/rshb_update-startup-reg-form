package ru.rshb.intech.digital.content.provider.elasticsearch.mapper;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.content.model.Article;
import ru.rshb.intech.digital.content.provider.api.ModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.ArticleDto;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Component
public class ArticleModelMapper implements ModelMapper<ArticleDto, Article> {

  @Nonnull
  @Override
  public Article mapFromDto(@Nonnull ArticleDto dto) {
    final var model = new Article();
    model.setType(dto.getPublicationType());
    model.setId(dto.getExternalId());
    model.setViews(defaultIfNull(dto.getViews(), ContentDefaults.VIEWS_NUM));
    model.setLikes(defaultIfNull(dto.getLikes(), ContentDefaults.LIKES_NUM));
    model.setUrl(dto.getUrl());
    model.setSlug(dto.getSlug());
    model.setTitle(dto.getTitle());
    model.setSummary(dto.getSummary());
    model.setLabels(dto.getCategories());
    model.setPublishDate(dto.getPublicationDate());
    model.setImage(dto.getImage());
    return model;
  }
}
