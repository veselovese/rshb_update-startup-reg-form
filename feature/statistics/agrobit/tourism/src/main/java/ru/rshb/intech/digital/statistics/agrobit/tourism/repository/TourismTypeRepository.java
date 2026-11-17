package ru.rshb.intech.digital.statistics.agrobit.tourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.statistics.agrobit.tourism.entity.TourismType;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public interface TourismTypeRepository extends JpaRepository<TourismType, Long> {

  @Nonnull
  Optional<TourismType> findByExternalIdIgnoreCase(@Nonnull String externalId);

  @Nonnull
  List<TourismType> findAllByEnabledIsTrueOrderByTitleAsc();

}
