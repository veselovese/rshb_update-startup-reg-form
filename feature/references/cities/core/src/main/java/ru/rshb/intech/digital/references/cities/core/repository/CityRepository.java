package ru.rshb.intech.digital.references.cities.core.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.references.cities.entity.City;

import jakarta.annotation.Nonnull;
import java.util.List;

public interface CityRepository extends JpaRepository<City, String> {

  @Nonnull
  List<City> findByTitleContainingIgnoreCaseOrderByTitle(String titleTemplate, Pageable pageable);

}
