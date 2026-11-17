package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadDirection;

import java.util.List;
import java.util.Optional;

public interface AgrotechOlympiadDirectionRepository extends JpaRepository<AgrotechOlympiadDirection, Long> {

  @Nonnull
  Optional<AgrotechOlympiadDirection> findByExternalIdIgnoreCase(@Nonnull String directionId);

  @Nonnull
  List<AgrotechOlympiadDirection> findAllByDisabledIsFalseOrderById();

}
