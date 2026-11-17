package ru.rshb.intech.digital.feature.contests.patent.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.contests.patent.entity.PatentDefCityOption;

import java.util.List;

public interface PatentDefCityRepository extends JpaRepository<PatentDefCityOption, Long> {

  @Nonnull
  List<PatentDefCityOption> findAllByDisabledIsFalseOrderById();

}
