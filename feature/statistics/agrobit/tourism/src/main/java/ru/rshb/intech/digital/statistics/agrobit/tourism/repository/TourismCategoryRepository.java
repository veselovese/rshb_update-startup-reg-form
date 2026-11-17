package ru.rshb.intech.digital.statistics.agrobit.tourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.statistics.agrobit.tourism.entity.TourismCategory;

import jakarta.annotation.Nonnull;
import java.util.List;

public interface TourismCategoryRepository extends JpaRepository<TourismCategory, Long> {

  @Nonnull
  List<TourismCategory> findAllByEnabledIsTrueOrderByTitleAsc();

}
