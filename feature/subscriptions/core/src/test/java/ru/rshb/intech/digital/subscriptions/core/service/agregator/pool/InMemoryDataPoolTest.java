package ru.rshb.intech.digital.subscriptions.core.service.agregator.pool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.model.DataItem;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.model.EventsComparator;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.model.NewsComparator;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.model.PodcastComparator;
import ru.rshb.intech.digital.subscriptions.entity.CategoryType;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionCategory;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryDataPoolTest {

  private DataPool dataPool;

  @BeforeEach
  void setUp() {

    final var dataItems = List.of(
        DataItem.builder()
            .title("Новость банка 1")
            .publicationDate(LocalDate.now())
            .categories(List.of("Новости банка"))
            .categoryType(CategoryType.NEWS)
            .build(),
        DataItem.builder()
            .title("Новость банка 2")
            .publicationDate(LocalDate.now().minusDays(1))
            .categories(List.of("Новости банка"))
            .categoryType(CategoryType.NEWS)
            .build(),
        DataItem.builder()
            .title("Событие разработки")
            .startDate(LocalDate.now().minusDays(3))
            .endDate(LocalDate.now().plusDays(10))
            .categories(List.of("Разработка"))
            .categoryType(CategoryType.EVENTS)
            .build(),
        DataItem.builder()
            .title("Подкаст цифровой грядки")
            .publicationDate(LocalDate.now().minusDays(2))
            .categories(List.of("Цифровая грядка"))
            .categoryType(CategoryType.PODCASTS)
            .build()
    );

    this.dataPool = new InMemoryDataPool(dataItems);
  }

  @Test
  void isEmpty() {
    assertFalse(dataPool.isEmpty());
  }

  @Test
  void size() {
    System.out.println(dataPool);
    assertEquals(4, dataPool.size());
  }

  @Test
  void findByCategoryType() {
    final List<DataItem> found = dataPool.findByCategoryType(CategoryType.NEWS);
    assertNotNull(found);
    assertEquals(2, found.size());
    System.out.println(found);
  }

  @Test
  void sliceBy() {

    final var categories = Set.of(
        SubscriptionCategory.builder()
            .type(CategoryType.NEWS)
            .title("Новости банка")
            .build(),
        SubscriptionCategory.builder()
            .type(CategoryType.NEWS)
            .title("Технологии")
            .build(),
        SubscriptionCategory.builder()
            .type(CategoryType.EVENTS)
            .title("Разработка")
            .build(),
        SubscriptionCategory.builder()
            .type(CategoryType.PODCASTS)
            .title("Цифровая грядка")
            .build()
    );

    final DataPool slice = dataPool.sliceBy(
        SlicingOptions.create()
            .setCategories(categories)
            .setOptions(CategoryType.NEWS, new SlicingOptions.CategoryOptions(0, 5, new NewsComparator()))
            .setOptions(CategoryType.EVENTS, new SlicingOptions.CategoryOptions(0, 5, new EventsComparator()))
            .setOptions(CategoryType.PODCASTS, new SlicingOptions.CategoryOptions(0, 100, new PodcastComparator()))
    );
    assertNotNull(slice);
    assertFalse(slice.isEmpty());
    assertEquals(4, slice.size());
    System.out.println(slice);
  }

  @Test
  void sliceBy_popular() {

    final var categories = Set.of(
        SubscriptionCategory.builder()
            .type(CategoryType.ALL)
            .build()
    );

    final DataPool slice = dataPool.sliceBy(
        SlicingOptions.create()
            .setCategories(categories)
            .setOptions(CategoryType.NEWS, new SlicingOptions.CategoryOptions(0, 5, new NewsComparator()))
            .setOptions(CategoryType.EVENTS, new SlicingOptions.CategoryOptions(0, 5, new EventsComparator()))
            .setOptions(CategoryType.PODCASTS, new SlicingOptions.CategoryOptions(0, 100, new PodcastComparator()))
    );

    assertNotNull(slice);
    assertFalse(slice.isEmpty());
    assertEquals(4, slice.size());
    System.out.println(slice);
  }

  @Test
  void testToString() {
    final String printable = dataPool.toString();
    assertFalse(StringUtils.isBlank(printable));
    System.out.println(printable);
  }
}