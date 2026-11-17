package ru.rshb.intech.digital.publications.core.service.publication;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import ru.rshb.intech.digital.content.model.ItemType;
import ru.rshb.intech.digital.content.model.Publication;
import ru.rshb.intech.digital.content.provider.api.GeneralPublicationService;
import ru.rshb.intech.digital.content.provider.api.PublicationContentService;
import ru.rshb.intech.digital.subsystem.content.index.entity.ContentIndexEntry;
import ru.rshb.intech.digital.subsystem.content.index.service.ContentIndexService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GeneralPublicationContentServiceImpl implements GeneralPublicationService {

  private final Map<ItemType, PublicationContentService<? extends Publication>> contentServiceMap;
  private final ContentIndexService contentIndexService;


  public GeneralPublicationContentServiceImpl(@Nonnull List<PublicationContentService<? extends Publication>> services,
                                              @Nonnull ContentIndexService contentIndexService) {

    this.contentServiceMap = services.stream()
        .collect(Collectors.toMap(PublicationContentService::getAssotiatedItemType, Function.identity()));
    this.contentIndexService = contentIndexService;
  }

  @Nullable
  @Override
  public Publication getById(@Nonnull String pubId) {

    final var pubIndexEntry = contentIndexService.getById(pubId);

    if (pubIndexEntry != null) {
      return getFromService(pubIndexEntry);
    }

    return searchInAllServices(pubId);
  }

  @Nullable
  private Publication getFromService(@Nonnull ContentIndexEntry contentIndexEntry) {

    final var pubId = contentIndexEntry.getId();
    final var pubType = contentIndexEntry.getItemType();

    final var contentService = contentServiceMap.get(pubType);

    return contentService.findById(pubId)
        .orElse(null);
  }

  @Nullable
  private Publication searchInAllServices(@Nonnull String pubId) {

    for (final var service : contentServiceMap.values()) {

      final var pubSearchResult = service.findById(pubId);

      if (pubSearchResult.isEmpty()) {
        continue;
      }

      contentIndexService.putInIndex(pubId, service.getAssotiatedItemType());
      return pubSearchResult.get();
    }

    return null;
  }
}
