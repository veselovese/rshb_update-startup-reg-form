package ru.rshb.intech.digital.subscriptions.core.service.mailing;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.NotificationService;
import ru.rshb.intech.digital.notifications.api.message.BasicMessage;
import ru.rshb.intech.digital.notifications.api.message.Message;
import ru.rshb.intech.digital.notifications.api.message.MessageContent;
import ru.rshb.intech.digital.notifications.mail.channel.EmailChannels;
import ru.rshb.intech.digital.subscriptions.core.repository.SubscriptionRepository;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.collector.DataCollector;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.collector.DataCollectorOptions;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.model.EventsComparator;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.model.NewsComparator;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.model.PodcastComparator;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.pool.DataPool;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.pool.SlicingOptions;
import ru.rshb.intech.digital.subscriptions.core.service.category.SubscriptionCategoryService;
import ru.rshb.intech.digital.subscriptions.core.service.deactivation.DeactivationLinkFactory;
import ru.rshb.intech.digital.subscriptions.entity.CategoryType;
import ru.rshb.intech.digital.subscriptions.entity.Subscription;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionCategory;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailerServiceImpl implements MailerService {

  private static final long NONE_COLLECTED_NUM = 0L;
  private static final String DEFAULT_NEWS_SUBJECT = I18n.of("mailing.subject");
  private static final int DEFAULT_PERIOD_WEEKS = 1;
  private static final int NEWS_MIN_ITEMS_NUM = 1;
  private static final int EVENTS_MIN_ITEMS_NUM = 1;
  private static final int PODCASTS_MIN_ITEMS_NUM = 0;

  private final DataCollector dataCollector;
  private final SubscriptionRepository subscriptionRepository;
  private final SubscriptionCategoryService subscriptionCategoryService;
  private final NotificationService notificationService;
  private final MailingDigestEmailTemplate emailTemplate;
  private final DeactivationLinkFactory deactivationLinkFactory;
  private final MailingDigestLimits mailingLimits;


  @Override
  public long collectAndSend() {

    // 0. Сформировать список получателей.
    final var subscribers = subscriptionRepository.findActiveSubscriptions();
    if (subscribers.isEmpty()) {
      log.info("No active subscribers found. Mailing aborted.");
      return NONE_COLLECTED_NUM;
    }
    log.info("Found {} active subscribers", subscribers.size());

    // 1. Собрать дайджест из всех данных за последний период (неделя).
    final var dataPool = collectDataForPeriod(DEFAULT_PERIOD_WEEKS, ChronoUnit.WEEKS);
    if (dataPool.isEmpty()) {
      log.info("Mailing digest data pool is empty. Mailing aborted");
      return NONE_COLLECTED_NUM;
    }
    log.info("Mailing digest data pool collected. Size: {}\n{}", dataPool.size(), dataPool);

    final var messages = subscribers.stream()
        .map(subscriber -> composeMailingData(subscriber, dataPool)) // 2. Для каждого получателя собрать подмножество данных
        .filter(Objects::nonNull)
        .map(this::newMessageWith) // 3. По каждому подмножеству данных создать письмо.
        .toList();
    log.info("Prepared messages: {}", messages.size());

    // 4. Поместить все письма в очередь на отправку.
    messages.forEach(notificationService::send);

    return messages.size();
  }

  @Nullable
  private MailingData composeMailingData(@Nonnull Subscription subscriber, @Nonnull DataPool dataPool) {

    log.info("Collecting mailing digest for: {}", subscriber.getId());

    final var categories = getCategoriesOf(subscriber);
    log.info("Categories ({}): {}", categories.size(), categories);

    final var slice = dataPool.sliceBy(slicingOptions(categories));
    log.info("Slice size: {}\n{}", slice.size(), slice);

    final var preparedData = new MailingData(subscriber, slice);

    if (!isEnoughDataForMailing(preparedData)) {
      log.info("Not enough data for mailing: {}", subscriber.getEmail());
      return null;
    }

    log.info("Adding subscriber digest for mailing: {}", subscriber.getId());
    return preparedData;
  }

  @Nonnull
  private SlicingOptions slicingOptions(@Nonnull Collection<SubscriptionCategory> categories) {
    return SlicingOptions.create()
        .setCategories(categories)
        .setOptions(
            CategoryType.NEWS,
            SlicingOptions.CategoryOptions.builder()
                .minItemsNumber(NEWS_MIN_ITEMS_NUM)
                .maxItemsNumber(mailingLimits.getNewsItemsMaxNumber())
                .comparator(new NewsComparator())
                .build()
        )
        .setOptions(
            CategoryType.EVENTS,
            SlicingOptions.CategoryOptions.builder()
                .minItemsNumber(EVENTS_MIN_ITEMS_NUM)
                .maxItemsNumber(mailingLimits.getEventsItemsMaxNumber())
                .comparator(new EventsComparator())
                .build()
        )
        .setOptions(
            CategoryType.PODCASTS,
            SlicingOptions.CategoryOptions.builder()
                .minItemsNumber(PODCASTS_MIN_ITEMS_NUM)
                .maxItemsNumber(mailingLimits.getPodcastsItemsMaxNumber())
                .comparator(new PodcastComparator())
                .build()
        );
  }

  @Nonnull
  private Set<SubscriptionCategory> getCategoriesOf(@Nonnull Subscription subscription) {
    final var activeCategories = subscription.getActiveCategories();
    final var isAllCategories = activeCategories.stream()
        .anyMatch(cat -> cat.getType() == CategoryType.ALL);
    return (isAllCategories)
        ? subscriptionCategoryService.findPopularCategories().getCategories()
        : activeCategories;
  }

  @Nonnull
  private DataPool collectDataForPeriod(long periodLength, @Nonnull TemporalUnit periodUnits) {

    final var periodStart = LocalDate.now().minus(periodLength, periodUnits);
    final var periodEnd = LocalDate.now();

    return dataCollector.collectData(
        DataCollectorOptions.builder()
            .periodStartDate(periodStart)
            .periodEndDate(periodEnd)
            .build()
    );
  }

  @Nonnull
  private Message newMessageWith(@Nonnull MailingData mailingData) {

    final var recipient = mailingData.getSubscription().getEmail();
    final var content = newEmailContent(mailingData);

    return BasicMessage.builder()
        .channels(Set.of(EmailChannels.MAILING))
        .recipients(Set.of(recipient))
        .collate(false)
        .content(
            MessageContent.builder()
                .subject(DEFAULT_NEWS_SUBJECT)
                .data(content.getBytes(StandardCharsets.UTF_8))
                .build()
        )
        .build();
  }

  @Nonnull
  private String newEmailContent(@Nonnull MailingData mailingData) {

    final var data = mailingData.getData();
    final var sub = mailingData.getSubscription();
    log.info("Composing email content for: {}", sub.getEmail());

    final var news = data.findByCategoryType(CategoryType.NEWS);
    log.info("News: {}", news.size());

    final var events = data.findByCategoryType(CategoryType.EVENTS);
    log.info("Events: {}", events.size());

    final var podcasts = data.findByCategoryType(CategoryType.PODCASTS);
    log.info("Podcasts: {}", podcasts.size());

    final var unsubscribeLink = deactivationLinkFactory.createNewLink(sub.getDeactivationCode());
    log.info("Unsubscribe link: {}", unsubscribeLink);

    return emailTemplate.toHtml(
        MailingDigest.builder()
            .news(news)
            .events(events)
            .podcasts(podcasts)
            .deactivationUrl(unsubscribeLink)
            .build()
    );
  }

  private boolean isEnoughDataForMailing(@Nonnull MailingData mailingData) {
    return !mailingData.getData().isEmpty();
  }

  @Getter
  @RequiredArgsConstructor
  private static class MailingData {

    private final Subscription subscription;
    private final DataPool data;
  }
}
