package ru.rshb.intech.digital.content.provider.elasticsearch.mapper;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.content.model.Book;
import ru.rshb.intech.digital.content.provider.api.ModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.BookDto;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Component
public class BookModelMapper implements ModelMapper<BookDto, Book> {

  @Nonnull
  @Override
  public Book mapFromDto(@Nonnull BookDto dto) {
    final var model = new Book();
    model.setId(dto.getExternalId());
    model.setTitle(dto.getTitle());
    model.setViews(defaultIfNull(dto.getViews(), ContentDefaults.VIEWS_NUM));
    model.setLikes(defaultIfNull(dto.getLikes(), ContentDefaults.LIKES_NUM));
    model.setPublishDate(dto.getPublicationDate());
    model.setSlug(dto.getSlug());
    model.setImage(dto.getImage());
    model.setLabels(dto.getLabels());
    return model;
  }
}
