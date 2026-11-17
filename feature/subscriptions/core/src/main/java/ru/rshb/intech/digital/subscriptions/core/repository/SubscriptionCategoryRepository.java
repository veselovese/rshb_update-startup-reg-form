package ru.rshb.intech.digital.subscriptions.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rshb.intech.digital.subscriptions.entity.CategoryType;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionCategory;

import java.util.List;

public interface SubscriptionCategoryRepository extends JpaRepository<SubscriptionCategory, Long> {

  @Nonnull
  @Query(
      """
      select cat
      from SubscriptionCategory cat
      where (cat.deleted is null or cat.deleted = false) and cat.type <> 'all'
      """
  )
  List<SubscriptionCategory> findAllCategories();

  @Nonnull
  @Query(
      """
      select cat
      from SubscriptionCategory cat
      where cat.type = 'all'
      """
  )
  SubscriptionCategory getPopularCategory();

  @Nonnull
  @Query(
      """
      select cat
      from SubscriptionCategory cat
      where cat.type = :categoryType
      and (cat.deleted is null or cat.deleted = false)
      and cat.externalId in :ids
      """
  )
  List<SubscriptionCategory> findAllByExternalIds(@Nonnull CategoryType categoryType, @Nonnull List<String> ids);
}
