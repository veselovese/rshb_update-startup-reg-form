package ru.rshb.intech.digital.content.provider.api;


import ru.rshb.intech.digital.content.model.ItemType;
import ru.rshb.intech.digital.content.model.Podcast;

import jakarta.annotation.Nonnull;
import java.time.LocalDate;
import java.util.List;

public interface PodcastContentService extends PublicationContentService<Podcast> {

  @Nonnull
  List<Podcast> findByDatePeriod(@Nonnull LocalDate periodStart, @Nonnull LocalDate periodEnd);

  @Nonnull
  @Override
  default ItemType getAssotiatedItemType() {
    return ItemType.PODCAST;
  }
}
