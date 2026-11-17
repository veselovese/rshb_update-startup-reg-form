package ru.rshb.intech.digital.subscriptions.core.validation;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.rshb.intech.digital.subscriptions.core.exception.SubscriptionValidationException;
import ru.rshb.intech.digital.subscriptions.core.repository.BannedEmailRegistry;
import ru.rshb.intech.digital.subscriptions.core.service.category.SubscriptionCategoryService;
import ru.rshb.intech.digital.subscriptions.entity.CategoryType;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionCategoriesData;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionCategory;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionRequestData;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Disabled
@SpringBootTest(
    classes = SubscriptionDataValidator.class
)
class SubscriptionDataValidatorTest {

  @Autowired
  private SubscriptionDataValidator subscriptionDataValidator;

  @MockBean
  private SubscriptionCategoryService subscriptionCategoryService;

  @MockBean
  private BannedEmailRegistry bannedEmailRegistry;


  @Test
  void validate() {

    when(subscriptionCategoryService.findUnknownCategories(anySet()))
        .thenReturn(Collections.emptySet());

    when(bannedEmailRegistry.existsByEmailAndDeletedFalse(anyString()))
        .thenReturn(false);

    final var category = new SubscriptionCategory();
    category.setType(CategoryType.PODCASTS);
    category.setExternalId("d0c474bc-19f5-48e4-828d-bec17b77e6cc");

    final SubscriptionCategoriesData categoriesData = new SubscriptionCategoriesData(false, Set.of(category));

    final SubscriptionRequestData requestData = SubscriptionRequestData.builder()
        .email("none@none.com")
        .categoriesData(categoriesData)
        .build();

    final SubscriptionRequestData validatedData = subscriptionDataValidator.validate(requestData);
    assertNotNull(validatedData);
    assertEquals("none@none.com", validatedData.getEmail());
    assertEquals(1, validatedData.getCategoriesData().getCategories().size());
  }

  @Test
  void validate_no_categories() {

    final SubscriptionCategoriesData categoriesData = new SubscriptionCategoriesData(false, Collections.emptySet());
    final SubscriptionRequestData requestData = SubscriptionRequestData.builder()
        .email("none@none.com")
        .categoriesData(categoriesData)
        .build();

    assertThrows(SubscriptionValidationException.class, () -> {
      subscriptionDataValidator.validate(requestData);
    });
  }

  @Test
  void validate_email_banned() {

    when(subscriptionCategoryService.findUnknownCategories(anySet()))
        .thenReturn(Collections.emptySet());

    when(bannedEmailRegistry.existsByEmailAndDeletedFalse(anyString()))
        .thenReturn(true);

    final var category = new SubscriptionCategory();
    category.setType(CategoryType.PODCASTS);
    category.setExternalId("d0c474bc-19f5-48e4-828d-bec17b77e6cc");

    final SubscriptionCategoriesData categoriesData = new SubscriptionCategoriesData(false, Set.of(category));

    final SubscriptionRequestData requestData = SubscriptionRequestData.builder()
        .email("none@none.com")
        .categoriesData(categoriesData)
        .build();

    assertThrows(SubscriptionValidationException.class, () -> {
      subscriptionDataValidator.validate(requestData);
    });
  }
}