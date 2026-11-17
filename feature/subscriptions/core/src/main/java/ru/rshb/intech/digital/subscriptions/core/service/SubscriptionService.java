package ru.rshb.intech.digital.subscriptions.core.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.subscriptions.entity.Subscription;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionFeedback;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionRequestData;

public interface SubscriptionService {

  @Nonnull
  SubscriptionResult subscribe(@Nonnull SubscriptionRequestData subscriptionData);

  @Nonnull
  Subscription activateSubscription(@Nonnull String activationCode);

  @Nonnull
  Subscription unsubscribe(@Nonnull String deactivationCode, @Nonnull SubscriptionFeedback feedbackReport);
}
