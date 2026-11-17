package ru.rshb.intech.digital.subscriptions.core.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.subscriptions.core.exception.SubscriptionException;
import ru.rshb.intech.digital.subscriptions.core.exception.SubscriptionNotFoundException;
import ru.rshb.intech.digital.subscriptions.core.repository.SubscriptionFeedbackReportRepository;
import ru.rshb.intech.digital.subscriptions.core.repository.SubscriptionRepository;
import ru.rshb.intech.digital.subscriptions.core.service.activation.ActivationCodeFactory;
import ru.rshb.intech.digital.subscriptions.core.service.category.SubscriptionCategoryService;
import ru.rshb.intech.digital.subscriptions.core.service.deactivation.DeactivationCodeFactory;
import ru.rshb.intech.digital.subscriptions.core.validation.SubscriptionDataValidator;
import ru.rshb.intech.digital.subscriptions.entity.Subscription;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionCategoriesData;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionFeedback;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionRequestData;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

  private final SubscriptionRepository subscriptionRepository;
  private final SubscriptionCategoryService subscriptionCategoryService;
  private final MailNotificationService notificationService;
  private final ActivationCodeFactory activationCodeFactory;
  private final DeactivationCodeFactory deactivationCodeFactory;
  private final SubscriptionDataValidator subscriptionDataValidator;
  private final SubscriptionFeedbackReportRepository feedbackReportRepository;


  @Nonnull
  @Override
  @Transactional
  public SubscriptionResult subscribe(@Nonnull SubscriptionRequestData subscriptionData) {

    final var validatedData = subscriptionDataValidator.validate(subscriptionData);
    final var existingCategories = validatedData.getCategoriesData().isPopular()
        ? subscriptionCategoryService.findPopularCategories()
        : subscriptionCategoryService.findCategories(validatedData.getCategoriesData());

    if (CollectionUtils.isEmpty(existingCategories.getCategories())) {
      throw SubscriptionException.noCategoriesFound();
    }

    final var email = subscriptionData.getEmail();
    final var existingSub = subscriptionRepository.findByEmail(email)
        .orElse(null);

    if (existingSub != null) {
      renewSubscription(existingSub, existingCategories);
      log.info("Subscription [{}] renewed", existingSub.getId());
      return SubscriptionResult.RENEWED;
    }

    final var newSub = createNewSubscription(email, existingCategories);
    log.info("New subscription: {}", newSub.getId());

    return SubscriptionResult.CREATED;
  }

  @Nonnull
  private Subscription createNewSubscription(@Nonnull String email, @Nonnull SubscriptionCategoriesData categories) {

    final var subscription = Subscription.builder()
        .email(email)
        .creationDate(LocalDateTime.now())
        .activationCode(activationCodeFactory.createNewCode())
        .pendingCategories(categories.getCategories())
        .build();

    return saveAndActivate(subscription);
  }

  @Nonnull
  private Subscription renewSubscription(@Nonnull Subscription sub,
                                         @Nonnull SubscriptionCategoriesData newCategories) {

    sub.setRenewalDate(LocalDateTime.now());
    sub.setActivationCode(activationCodeFactory.createNewCode());
    sub.setActivationDate(null);
    sub.setDeactivationCode(null);
    sub.setDeactivationDate(null);
    sub.setPendingCategories(newCategories.getCategories());

    return saveAndActivate(sub);
  }

  @Nonnull
  private Subscription saveAndActivate(@Nonnull Subscription subscription) {
    final var savedSub = subscriptionRepository.save(subscription);
    notificationService.sendActivationLetter(savedSub.getEmail(), savedSub.getActivationCode());
    return savedSub;
  }

  @Nonnull
  @Override
  @Transactional
  public Subscription activateSubscription(@Nonnull String activationCode) {

    log.debug("Activate subscription by code: {}", activationCode);

    final var sub = subscriptionRepository.findSubscriptionByActivationCode(activationCode)
        .orElseThrow(() -> SubscriptionNotFoundException.activationError(activationCode));

    sub.setDeactivationCode(deactivationCodeFactory.createNewCode());
    sub.setActivationDate(LocalDateTime.now());
    sub.setActivationCode(null);
    sub.setActiveCategories(sub.getPendingCategories());
    sub.setPendingCategories(null);

    final var updatedSub = subscriptionRepository.save(sub);

    log.info("Subscription [{}] activated", updatedSub.getId());
    return updatedSub;
  }

  @Nonnull
  @Override
  @Transactional
  public Subscription unsubscribe(@Nonnull String deactivationCode, @Nonnull SubscriptionFeedback feedbackReport) {

    log.debug("Deactivate subscription by code: {}", deactivationCode);

    final var sub = subscriptionRepository.findSubscriptionByDeactivationCode(deactivationCode)
        .orElseThrow(() -> SubscriptionNotFoundException.deactivationError(deactivationCode));

    // Терминализация подписки
    sub.setDeactivationDate(LocalDateTime.now());
    sub.setDeactivationCode(null);
    sub.setActiveCategories(null);
    sub.setPendingCategories(null);
    final var updatedSub = subscriptionRepository.save(sub);

    // Сохраняем фидбэк от пользователя, если есть что сохранять
    final boolean needToSaveFeedback = StringUtils.isNotBlank(feedbackReport.getReasons())
                                       || StringUtils.isNotBlank(feedbackReport.getFeedback());

    if (needToSaveFeedback) {
      feedbackReport.setEmail(sub.getEmail());
      feedbackReport.setSubmitDate(sub.getDeactivationDate());
      feedbackReportRepository.save(feedbackReport);
    }

    log.info("Subscription [{}] deactivated", updatedSub.getId());
    return updatedSub;
  }
}
