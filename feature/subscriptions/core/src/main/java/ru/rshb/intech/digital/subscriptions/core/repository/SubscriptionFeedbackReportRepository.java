package ru.rshb.intech.digital.subscriptions.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionFeedback;

public interface SubscriptionFeedbackReportRepository extends JpaRepository<SubscriptionFeedback, Long> {
}
