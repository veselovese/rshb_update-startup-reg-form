package ru.rshb.intech.digital.subscriptions.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class SubscriptionRequestData {
    private String email;
    private SubscriptionCategoriesData categoriesData;
}
