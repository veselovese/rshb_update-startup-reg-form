package ru.rshb.intech.digital.content.provider.api;


import ru.rshb.intech.digital.content.model.ItemType;
import ru.rshb.intech.digital.content.model.Publication;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.Optional;

public interface PublicationContentService<T extends Publication>  {

    @Nonnull
    ItemType getAssotiatedItemType();

    @Nonnull
    Optional<T> findById(@Nonnull String id);

    @Nullable
    default T getOrNull(@Nonnull String id) {
        return findById(id).orElse(null);
    }

    boolean isExistById(@Nonnull String id);

}
