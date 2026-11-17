package ru.rshb.intech.digital.notifications.internal.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rshb.intech.digital.notifications.entity.NotificationRecipient;
import ru.rshb.intech.digital.notifications.entity.NotificationTopic;

import java.util.Set;

public interface NotificationRecipientRepository extends JpaRepository<NotificationRecipient, Long> {

  @Query(
      """
      select nr.email
      from NotificationRecipient nr
      where :topic member of nr.topics
          and nr.enabled = true
          and nr.deleted = false
      """
  )
  Set<String> findEnabledEmailsByTopic(@Nonnull NotificationTopic topic);
}
