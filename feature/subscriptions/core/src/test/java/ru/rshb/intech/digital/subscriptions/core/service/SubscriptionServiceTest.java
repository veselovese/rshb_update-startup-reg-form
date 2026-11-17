package ru.rshb.intech.digital.subscriptions.core.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.rshb.intech.digital.notifications.api.NotificationService;
import ru.rshb.intech.digital.subscriptions.core.exception.SubscriptionValidationException;
import ru.rshb.intech.digital.subscriptions.core.repository.SubscriptionRepository;
import ru.rshb.intech.digital.subscriptions.core.service.activation.ActivationEmailTemplate;
import ru.rshb.intech.digital.subscriptions.core.service.activation.DefaultActivationLinkFactory;
import ru.rshb.intech.digital.subscriptions.core.service.activation.UuidActivationCodeFactory;
import ru.rshb.intech.digital.subscriptions.core.service.category.SubscriptionCategoryServiceImpl;
import ru.rshb.intech.digital.subscriptions.core.service.deactivation.DefaultDeactivationLinkFactory;
import ru.rshb.intech.digital.subscriptions.core.service.deactivation.UuidDeactivationCodeFactory;
import ru.rshb.intech.digital.subscriptions.core.test.AbstractIntegrationTest;
import ru.rshb.intech.digital.subscriptions.core.test.TestConfig;
import ru.rshb.intech.digital.subscriptions.core.test.TestPersistenceConfig;
import ru.rshb.intech.digital.subscriptions.core.validation.SubscriptionDataValidator;
import ru.rshb.intech.digital.subscriptions.entity.CategoryType;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionCategoriesData;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionCategory;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionRequestData;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled
@SpringBootTest(
    classes = {
        SubscriptionServiceImpl.class,
        SubscriptionCategoryServiceImpl.class,
        MailNotificationServiceImpl.class,
        ActivationEmailTemplate.class,
        UuidActivationCodeFactory.class,
        DefaultActivationLinkFactory.class,
        DefaultDeactivationLinkFactory.class,
        UuidDeactivationCodeFactory.class,
        SubscriptionDataValidator.class
    }
)
@Import({TestConfig.class, TestPersistenceConfig.class})
class SubscriptionServiceTest extends AbstractIntegrationTest {

  @Autowired
  private SubscriptionService subscriptionService;

  @Autowired
  private SubscriptionRepository subscriptionRepository;

  @MockBean
  private NotificationService notificationService;

  @Test
  void subscribe_popular() {

    final var categories = new SubscriptionCategoriesData();
    categories.setPopular(true);

    final var requestData = new SubscriptionRequestData();
    requestData.setEmail("test@none.com");
    requestData.setCategoriesData(categories);

    final SubscriptionResult result = subscriptionService.subscribe(requestData);
    assertNotNull(result);
    assertEquals(SubscriptionResult.CREATED, result);
  }

  @Test
  void subscribe() {

    final var categories = new SubscriptionCategoriesData(
        Set.of(
            new SubscriptionCategory(CategoryType.NEWS, "8ccbd91f-7da1-4248-a286-bc48a9c64414"),
            new SubscriptionCategory(CategoryType.EVENTS, "6d591f20-b330-4d31-8bbf-e1143f80c322"),
            new SubscriptionCategory(CategoryType.PODCASTS, "e2c5cc66-bf39-48d7-9943-20b0bc3af153")
        )
    );

    final var requestData = new SubscriptionRequestData();
    requestData.setEmail("test0@none.com");
    requestData.setCategoriesData(categories);

    final SubscriptionResult result = subscriptionService.subscribe(requestData);
    assertNotNull(result);
    assertEquals(SubscriptionResult.CREATED, result);
  }

  @Test
  void subscribe_non_existing_category() {
    final var categories = new SubscriptionCategoriesData(
        Set.of(
            new SubscriptionCategory(CategoryType.NEWS, "8ccbd91f-7da1-4248-a286-bc48a9c64414"),
            new SubscriptionCategory(CategoryType.EVENTS, "abracadabra"),
            new SubscriptionCategory(CategoryType.PODCASTS, "e2c5cc66-bf39-48d7-9943-20b0bc3af153")
        )
    );

    final var requestData = new SubscriptionRequestData();
    requestData.setEmail("test1@none.com");
    requestData.setCategoriesData(categories);

    assertThrows(SubscriptionValidationException.class, () -> {
      subscriptionService.subscribe(requestData);
    });
  }

  @Test
  void subscribe_renew() {
    final var categories = new SubscriptionCategoriesData(
        Set.of(
            new SubscriptionCategory(CategoryType.NEWS, "8ccbd91f-7da1-4248-a286-bc48a9c64414"),
            new SubscriptionCategory(CategoryType.EVENTS, "6d591f20-b330-4d31-8bbf-e1143f80c322"),
            new SubscriptionCategory(CategoryType.PODCASTS, "e2c5cc66-bf39-48d7-9943-20b0bc3af153")
        )
    );

    final var requestData = new SubscriptionRequestData();
    requestData.setEmail("test2@none.com");
    requestData.setCategoriesData(categories);

    assertFalse(subscriptionRepository.findByEmail("test2@none.com").isPresent());
    subscriptionService.subscribe(requestData);
    assertEquals(1, subscriptionRepository.findAllByEmail("test2@none.com").size());

    final SubscriptionResult result = subscriptionService.subscribe(requestData);
    assertNotNull(result);
    assertEquals(SubscriptionResult.RENEWED, result);
    assertEquals(1, subscriptionRepository.findAllByEmail("test2@none.com").size());
  }

  @Test
  void activateSubscription() {

  }

  @Test
  void unsubscribe() {

  }
}