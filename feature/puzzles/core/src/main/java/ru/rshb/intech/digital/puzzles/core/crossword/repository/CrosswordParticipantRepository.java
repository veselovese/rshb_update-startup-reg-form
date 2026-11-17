package ru.rshb.intech.digital.puzzles.core.crossword.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rshb.intech.digital.puzzles.entity.CrosswordParticipant;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CrosswordParticipantRepository extends JpaRepository<CrosswordParticipant, Long> {

  boolean existsByPhone(@Nonnull String phone);

  @Nonnull
  Optional<CrosswordParticipant> findFirstByOrderBySubmitDateAsc();

  @Query(
      """
      select count(r)
      from CrosswordParticipant r
      where r.submitDate >= :periodStart and r.submitDate <= :periodEnd
      """
  )
  long findCountInPeriod(LocalDateTime periodStart, LocalDateTime periodEnd);
}
