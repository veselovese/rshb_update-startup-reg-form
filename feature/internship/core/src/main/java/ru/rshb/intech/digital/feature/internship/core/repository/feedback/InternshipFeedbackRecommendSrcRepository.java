package ru.rshb.intech.digital.feature.internship.core.repository.feedback;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.internship.core.entity.RecommendSrc;

import java.util.List;

public interface InternshipFeedbackRecommendSrcRepository extends JpaRepository<RecommendSrc, Long> {

  @Nonnull
  List<RecommendSrc> findAllByDeletedIsFalse();
}
