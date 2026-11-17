package ru.rshb.intech.digital.subscriptions.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rshb.intech.digital.subscriptions.entity.Subscription;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

  @Nonnull
  Optional<Subscription> findSubscriptionByActivationCode(@Nonnull String activationCode);

  @Nonnull
  Optional<Subscription> findSubscriptionByDeactivationCode(@Nonnull String deactivationCode);

  @Nonnull
  @Query(
      """
      select s
      from Subscription s
      where s.email = :email and s.blockingDate is null
      """
  )
  Optional<Subscription> findByEmail(@Nonnull String email);

  @Nonnull
  List<Subscription> findAllByEmail(@Nonnull String email);

  @Nonnull
  Optional<Subscription> findFirstByOrderByCreationDateAsc();

  @Query(
      """
      select count(r)
      from Subscription r
      where r.creationDate >= :periodStart and r.creationDate <= :periodEnd
      """
  )
  long findCountInPeriod(LocalDateTime periodStart, LocalDateTime periodEnd);


  @Query(
      """
      select s
      from Subscription s
      where (s.blockingDate is null)
          and (s.deactivationDate is null)
          and (s.activationDate is not null)
      order by s.creationDate asc
      """
  )
  List<Subscription> findActiveSubscriptions();

  @Query(
      """
      select count(r)
      from Subscription r
      where (r.blockingDate is null)
          and (r.deactivationDate is null)
          and (r.activationDate is not null)
      """
  )
  long findActiveSubscriptionsCount();

  @Query(
      """
      select count(r)
      from Subscription r
      where
          (r.blockingDate is null) and (r.deactivationDate is null) and (r.activationDate is not null)
          and (r.creationDate >= :periodStart and r.creationDate <= :periodEnd)
      """
  )
  long findActiveCountInPeriod(LocalDateTime periodStart, LocalDateTime periodEnd);

  @Query(
      """
      select count(r)
      from Subscription r
      where r.deactivationDate is not null
      """
  )
  long findUnsubscribedCount();

  @Query(
      """
      select count(r)
      from Subscription r
      where (r.deactivationDate is not null)
          and (r.deactivationDate >= :periodStart and r.deactivationDate <= :periodEnd)
      """
  )
  long findUnsubscribedCountInPeriod(LocalDateTime periodStart, LocalDateTime periodEnd);

  @Nonnull
  Optional<Subscription> findFirstByDeactivationDateIsNotNullOrderByDeactivationDateAsc();
}
