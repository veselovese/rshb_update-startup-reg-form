package ru.rshb.intech.digital.feature.statistics.agrobit.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.entity.EduType;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.entity.Faculty;

import jakarta.annotation.Nonnull;
import java.util.List;

public interface AgrobitStatsFacultyRepository extends JpaRepository<Faculty, Long> {

  @Nonnull
  List<Faculty> findAllByType(@Nonnull EduType eduType);

}
