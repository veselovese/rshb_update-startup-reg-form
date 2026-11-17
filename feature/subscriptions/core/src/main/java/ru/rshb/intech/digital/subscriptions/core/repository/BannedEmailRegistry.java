package ru.rshb.intech.digital.subscriptions.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.subscriptions.entity.BannedEmail;

public interface BannedEmailRegistry extends JpaRepository<BannedEmail, Long> {

  boolean existsByEmailAndDeletedFalse(@Nonnull String email);
}
