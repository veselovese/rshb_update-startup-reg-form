package ru.rshb.intech.digital.subscriptions.core.service.mailing;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MailingDigestLimits {

  private final int newsItemsMaxNumber;
  private final int eventsItemsMaxNumber;
  private final int podcastsItemsMaxNumber;

  public MailingDigestLimits(
      @Value("${subscriptions.digest.limits.news}") Integer newsItemsMaxNumber,
      @Value("${subscriptions.digest.limits.events}") Integer eventsItemsMaxNumber,
      @Value("${subscriptions.digest.limits.podcasts}") Integer podcastsItemsMaxNumber
  ) {
    this.newsItemsMaxNumber = newsItemsMaxNumber;
    this.eventsItemsMaxNumber = eventsItemsMaxNumber;
    this.podcastsItemsMaxNumber = podcastsItemsMaxNumber;
  }
}
