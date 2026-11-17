package ru.rshb.intech.digital.service.config;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.content.model.Publication;
import ru.rshb.intech.digital.content.provider.api.ArticleContentService;
import ru.rshb.intech.digital.content.provider.api.BooksContentService;
import ru.rshb.intech.digital.content.provider.api.GeneralPublicationService;
import ru.rshb.intech.digital.content.provider.api.InfographicsContentService;
import ru.rshb.intech.digital.content.provider.api.InnovationContentService;
import ru.rshb.intech.digital.content.provider.api.LectureContentService;
import ru.rshb.intech.digital.content.provider.api.PodcastContentService;
import ru.rshb.intech.digital.content.provider.api.PublicationContentService;
import ru.rshb.intech.digital.content.provider.api.TechnologyContentService;
import ru.rshb.intech.digital.content.provider.api.ToolContentService;
import ru.rshb.intech.digital.content.provider.api.TrendContentService;
import ru.rshb.intech.digital.publications.core.service.publication.GeneralPublicationContentServiceImpl;
import ru.rshb.intech.digital.subsystem.content.index.service.ContentIndexService;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ContentConfig {

  @Bean
  public GeneralPublicationService publicationContentService(PublicationContentServiceGroup serviceGroup,
                                                             ContentIndexService contentIndexService) {

    return new GeneralPublicationContentServiceImpl(serviceGroup.toList(), contentIndexService);
  }

  @Component
  @RequiredArgsConstructor
  public static class PublicationContentServiceGroup {

    private final ArticleContentService articleContentService;
    private final PodcastContentService podcastContentService;
    private final InnovationContentService innovationContentService;
    private final InfographicsContentService infographicsContentService;
    private final BooksContentService booksContentService;
    private final LectureContentService lectureContentService;
    private final TrendContentService trendContentService;
    private final ToolContentService toolContentService;
    private final TechnologyContentService technologyContentService;

    @Nonnull
    public List<PublicationContentService<? extends Publication>> toList() {
      final var services = new ArrayList<PublicationContentService<? extends Publication>>();
      services.add(articleContentService);
      services.add(podcastContentService);
      services.add(innovationContentService);
      services.add(infographicsContentService);
      services.add(booksContentService);
      services.add(lectureContentService);
      services.add(trendContentService);
      services.add(toolContentService);
      services.add(technologyContentService);
      return services;
    }
  }
}
