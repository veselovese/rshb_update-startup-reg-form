package ru.rshb.intech.digital.feature.innovations.core.repository.general;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rshb.intech.digital.feature.innovations.core.entity.GeneralRequest;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GeneralRequestRepository extends JpaRepository<GeneralRequest, Long> {

  boolean existsByPhone(@Nonnull String phone);

  @Nonnull
  Optional<GeneralRequest> findFirstByOrderBySubmitDateAsc();

  @Query(
      """
      select count(r)
      from GeneralRequest r
      where r.submitDate >= :periodStart and r.submitDate <= :periodEnd
      """
  )
  long findCountInPeriod(LocalDateTime periodStart, LocalDateTime periodEnd);
}
