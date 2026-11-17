package ru.rshb.intech.digital.feature.internship.core.repository.education;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.internship.core.entity.EducationStage;

import java.util.List;
import java.util.Optional;

public interface EducationStageRepository extends JpaRepository<EducationStage, Long> {

  @Nonnull
  List<EducationStage> findAllByEnabledIsTrue();

  @Nonnull
  Optional<EducationStage> findByTitleIgnoreCase(@Nonnull String title);
}
