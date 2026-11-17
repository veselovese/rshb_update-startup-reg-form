package ru.rshb.intech.digital.subscriptions.core.service.agregator.collector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.content.model.Article;
import ru.rshb.intech.digital.content.model.Event;
import ru.rshb.intech.digital.content.model.Podcast;
import ru.rshb.intech.digital.content.provider.api.ArticleContentService;
import ru.rshb.intech.digital.content.provider.api.EventContentService;
import ru.rshb.intech.digital.content.provider.api.PodcastContentService;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.collector.mapper.ArticleDataMapper;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.collector.mapper.EventDataMapper;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.collector.mapper.PodcastDataMapper;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.model.DataItem;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.pool.DataPool;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.pool.InMemoryDataPool;

import jakarta.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataCollectorImpl implements DataCollector {

  private final ArticleDataMapper articleDataMapper;
  private final PodcastDataMapper podcastDataMapper;
  private final EventDataMapper eventDataMapper;
  private final ArticleContentService articleContentService;
  private final EventContentService eventContentService;
  private final PodcastContentService podcastContentService;


  public DataCollectorImpl(@Value("${external.url}") String externalUrl,
                           ArticleContentService articleContentService,
                           EventContentService eventContentService,
                           PodcastContentService podcastContentService) {

    this.articleDataMapper = new ArticleDataMapper(externalUrl);
    this.podcastDataMapper = new PodcastDataMapper(externalUrl);
    this.eventDataMapper = new EventDataMapper();
    this.articleContentService = articleContentService;
    this.eventContentService = eventContentService;
    this.podcastContentService = podcastContentService;
  }


  @Nonnull
  @Override
  public DataPool collectData(@Nonnull DataCollectorOptions options) {

    final List<DataItem> collectedData = new ArrayList<>();

    final List<Article> articles = articleContentService.findByDatePeriod(
        options.getPeriodStartDate(),
        options.getPeriodEndDate()
    );
    collectedData.addAll(articleDataMapper.dataItemsFrom(articles));

    final List<Event> events = eventContentService.findActual();
    collectedData.addAll(eventDataMapper.dataItemsFrom(events));

    final List<Podcast> podcasts = podcastContentService.findByDatePeriod(
        options.getPeriodStartDate(),
        options.getPeriodEndDate()
    );
    collectedData.addAll(podcastDataMapper.dataItemsFrom(podcasts));

    return new InMemoryDataPool(collectedData);
  }
}
