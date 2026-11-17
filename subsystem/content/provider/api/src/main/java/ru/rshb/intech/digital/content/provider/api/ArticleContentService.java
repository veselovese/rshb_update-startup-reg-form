package ru.rshb.intech.digital.content.provider.api;

import ru.rshb.intech.digital.content.model.Article;
import ru.rshb.intech.digital.content.model.ItemType;

import jakarta.annotation.Nonnull;
import java.time.LocalDate;
import java.util.List;

public interface ArticleContentService extends PublicationContentService<Article> {

  @Nonnull
  List<Article> findByDatePeriod(@Nonnull LocalDate periodStart, @Nonnull LocalDate periodEnd);

  @Nonnull
  @Override
  default ItemType getAssotiatedItemType() {
    return ItemType.ARTICLE;
  }
}

