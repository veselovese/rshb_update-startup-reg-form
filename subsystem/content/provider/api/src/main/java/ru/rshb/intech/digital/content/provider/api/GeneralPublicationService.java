package ru.rshb.intech.digital.content.provider.api;

import ru.rshb.intech.digital.content.model.Publication;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public interface GeneralPublicationService {

    @Nullable
    Publication getById(@Nonnull String pubId);
}
