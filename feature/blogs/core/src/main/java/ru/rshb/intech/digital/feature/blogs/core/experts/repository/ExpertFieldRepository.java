package ru.rshb.intech.digital.feature.blogs.core.experts.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertField;

import java.util.List;
import java.util.Set;

public interface ExpertFieldRepository extends JpaRepository<ExpertField, Long> {

  @Nonnull
  List<ExpertField> findAllByEnabledIsTrueOrderByTitle();

  boolean existsAllByTitleIsIn(@Nonnull Set<String> titles);

  boolean existsByTitleIgnoreCase(@Nonnull String title);
}
