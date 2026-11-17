package ru.rshb.intech.digital.notifications.internal.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rshb.intech.digital.notifications.entity.NotificationTopic;

import java.util.List;
import java.util.Optional;

public interface InternalNotificationTopicRepository extends JpaRepository<NotificationTopic, Long> {

  @Query(
      """
      select nt
      from NotificationTopic nt
      where nt.key = :topicKey and nt.deleted = false and nt.enabled = true
      """
  )
  @Nonnull
  Optional<NotificationTopic> findAvailableByKey(@Nonnull String topicKey);

  @Query(
      """
      select nt
      from NotificationTopic nt
      where nt.deleted = false and nt.enabled = true
      """
  )
  @Nonnull
  List<NotificationTopic> findAllAvailable();
}
