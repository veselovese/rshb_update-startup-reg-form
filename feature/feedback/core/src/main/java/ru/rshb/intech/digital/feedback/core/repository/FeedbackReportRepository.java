package ru.rshb.intech.digital.feedback.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FeedbackReportRepository extends JpaRepository<FeedbackReport, Long> {

    @Query(
        """
        select count(r)
        from FeedbackReport r
        where (r.submitDate >= :periodStart and r.submitDate <= :periodEnd)
        and (r.email = :email)
        """
    )
    long findCountBetweenDates(@Nonnull String email,
                               @Nonnull LocalDateTime periodStart,
                               @Nonnull LocalDateTime periodEnd);
    @Nonnull
    Optional<FeedbackReport> findFirstByOrderBySubmitDateAsc();

    @Query(
        """
        select count(r)
        from FeedbackReport r
        where r.submitDate >= :periodStart and r.submitDate <= :periodEnd
        """
    )
    long findCountInPeriod(@Nonnull LocalDateTime periodStart, @Nonnull LocalDateTime periodEnd);
}
