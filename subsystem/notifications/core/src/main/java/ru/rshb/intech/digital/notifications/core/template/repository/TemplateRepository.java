package ru.rshb.intech.digital.notifications.core.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.notifications.entity.NotificationTemplate;

import jakarta.annotation.Nonnull;
import java.util.Optional;

public interface TemplateRepository extends JpaRepository<NotificationTemplate, Long> {

  @Nonnull
  Optional<NotificationTemplate> findByKey(@Nonnull String templateKey);
}
