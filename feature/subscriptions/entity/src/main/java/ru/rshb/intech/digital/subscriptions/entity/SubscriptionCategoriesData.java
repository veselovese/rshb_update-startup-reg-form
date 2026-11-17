package ru.rshb.intech.digital.subscriptions.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.BooleanUtils;

import jakarta.annotation.Nonnull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubscriptionCategoriesData {

    @Setter
    private Boolean popular;

    @Getter
    private final Set<SubscriptionCategory> categories;


    public SubscriptionCategoriesData() {
        this(false);
    }

    public SubscriptionCategoriesData(boolean popular) {
        this(popular, Set.of());
    }

    public SubscriptionCategoriesData(boolean popular, @Nonnull Set<SubscriptionCategory> categories) {
        this.popular = popular;
        this.categories = new HashSet<>(categories);
    }

    public SubscriptionCategoriesData(@Nonnull Set<SubscriptionCategory> categories) {
        this(false, categories);
    }

    public boolean isPopular() {
        return BooleanUtils.toBooleanDefaultIfNull(popular, false);
    }

    @Nonnull
    public List<String> getExternalIds(@Nonnull CategoryType categoryType) {
        return categories.stream()
                .filter(cat -> cat.getType() == categoryType)
                .map(SubscriptionCategory::getExternalId)
                .toList();
    }
}
