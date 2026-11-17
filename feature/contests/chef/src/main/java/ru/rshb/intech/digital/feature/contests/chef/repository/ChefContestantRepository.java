package ru.rshb.intech.digital.feature.contests.chef.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rshb.intech.digital.feature.contests.chef.entity.ChefContestant;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ChefContestantRepository extends JpaRepository<ChefContestant, Long> {

  boolean existsByPhone(@Nonnull String phone);

  boolean existsBySolutionUrl(@Nonnull String url);


  @Nonnull
  Optional<ChefContestant> findFirstByOrderBySubmitDateAsc();

  @Query(
      """
      select count(r)
      from ChefContestant r
      where r.submitDate >= :periodStart and r.submitDate <= :periodEnd
      """
  )
  long findCountInPeriod(LocalDateTime periodStart, LocalDateTime periodEnd);
}
