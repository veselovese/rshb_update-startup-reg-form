package ru.rshb.intech.digital.subscriptions.core.service.agregator.collector.mapper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import ru.rshb.intech.digital.content.model.Article;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.model.DataItem;
import ru.rshb.intech.digital.subscriptions.entity.CategoryType;

import java.time.LocalDate;

import static org.apache.commons.lang3.StringUtils.startsWith;
import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.FULL_DATE;

@Slf4j
@RequiredArgsConstructor
public class ArticleDataMapper implements DataMapper<Article> {

  private static final String ARTICLES_CONTENT_PATH = "content/articles";
  private static final String ARTICLES_SLUG_TEMPLATE = "%s/articles/%s";

  private final String rootUrl;


  @Nonnull
  @Override
  public DataItem dataItemForm(@Nonnull Article article) {


    final var dataItem = new DataItem();

    final var url = StringUtils.defaultIfEmpty(
        article.getUrl(),
        urlFromSlug(article.getSlug())
    );
    dataItem.setUrl(url);

    dataItem.setTitle(article.getTitle());
    dataItem.setSummary(article.getSummary());
    dataItem.setCategoryType(CategoryType.NEWS);
    dataItem.setCategories(article.getLabels());
    dataItem.setImageUrl(articlesImageUrl(article.getImage()));

    final var pubDate = parsePublicationDate(article);
    if (pubDate != null) {
      dataItem.setPublicationDate(
          parsePublicationDate(article)
      );
      dataItem.setPublicationDateFormatted(
          FULL_DATE.format(dataItem.getPublicationDate())
      );
    }

    return dataItem;
  }

  @Nonnull
  private String urlFromSlug(@Nonnull String slug) {
    return String.format(ARTICLES_SLUG_TEMPLATE, rootUrl, slug);
  }

  @Nonnull
  private String articlesImageUrl(@Nonnull String imageName) {

    if (startsWith(imageName, ARTICLES_CONTENT_PATH)) {
      return rootUrl + "/" + imageName;
    }

    if (startsWith(imageName, "/" + ARTICLES_CONTENT_PATH)) {
      return rootUrl + imageName;
    }

    return rootUrl + "/" + ARTICLES_CONTENT_PATH + "/" + imageName;
  }

  @Nullable
  private LocalDate parsePublicationDate(@Nonnull Article article) {
    final var date = article.getPublishDate();
    if (date == null) {
      log.warn("Article [{}] has no publication date", article.getTitle());
    }
    return date;
  }
}
