package ru.rshb.intech.digital.subscriptions.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum CategoryType {

    ALL("all"),

    NEWS("news"),

    EVENTS("events"),

    PODCASTS("podcasts");

    private final String key;

    @Nullable
    public static CategoryType parseByKey(@Nullable String providedKey) {

        if (providedKey == null) {
            return null;
        }

        return Arrays.stream(values())
                .filter(v -> v.getKey().equalsIgnoreCase(providedKey))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Nonnull
    public static Set<CategoryType> specific() {
        return Arrays.stream(values())
                .filter(categoryType -> categoryType != ALL)
                .collect(Collectors.toSet());
    }
}
