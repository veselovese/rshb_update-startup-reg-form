package ru.rshb.intech.digital.content.provider.api;

import ru.rshb.intech.digital.content.model.Event;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public interface EventContentService {

    @Nonnull
    Optional<Event> findById(@Nonnull String id);

    @Nonnull
    List<Event> findActual();

}
