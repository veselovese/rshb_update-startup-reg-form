package ru.rshb.intech.digital.subscriptions.core.service.agregator.collector.mapper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import ru.rshb.intech.digital.content.model.Podcast;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.model.DataItem;
import ru.rshb.intech.digital.subscriptions.entity.CategoryType;

import java.time.LocalDate;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.FULL_DATE;

@Slf4j
public class PodcastDataMapper implements DataMapper<Podcast> {

  private static final String PODCASTS_URL_TEMPLATE = "%s/podcast";
  private final String rootPodcastUrl;


  public PodcastDataMapper(@Nonnull String rootUrl) {
    this.rootPodcastUrl = String.format(PODCASTS_URL_TEMPLATE, rootUrl);
  }


  @Nonnull
  @Override
  public DataItem dataItemForm(@Nonnull Podcast podcast) {

    final var dataItem = new DataItem();
    dataItem.setUrl(getPodcastUrl(podcast));
    dataItem.setTitle(podcast.getTitle());
    dataItem.setSubTitle(podcast.getChannel());
    dataItem.setSummary(podcast.getDescription());
    dataItem.setCategoryType(CategoryType.PODCASTS);
    dataItem.setCategories(List.of(podcast.getChannel()));

    final var pubDate = parsePublicationDate(podcast);
    if (pubDate != null) {
      dataItem.setPublicationDate(
          parsePublicationDate(podcast)
      );
      dataItem.setPublicationDateFormatted(
          FULL_DATE.format(dataItem.getPublicationDate())
      );
    }

    return dataItem;
  }

  @Nonnull
  private String getPodcastUrl(@Nonnull Podcast podcast) {
    final var slug = defaultIfEmpty(podcast.getSlug(), StringUtils.EMPTY).trim();
    return isNotBlank(slug)
        ? rootPodcastUrl + "/" + slug
        : rootPodcastUrl;
  }

  @Nullable
  private LocalDate parsePublicationDate(@Nonnull Podcast podcast) {
    final var date = podcast.getPublishDate();
    if (date == null) {
      log.warn("Podcast [{}] has no publication date", podcast.getTitle());
    }
    return date;
  }
}
