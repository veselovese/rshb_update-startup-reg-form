package ru.rshb.intech.digital.publications.core.service.views;

import ru.rshb.intech.digital.content.model.Publication;

import jakarta.annotation.Nonnull;

public interface PublicationViewsService {

  @Nonnull
  Long incrementViewsCount(@Nonnull Publication publication, @Nonnull String sessionId);

  @Nonnull
  Long getViewsCount(@Nonnull Publication publication, @Nonnull String sessionId);
}
