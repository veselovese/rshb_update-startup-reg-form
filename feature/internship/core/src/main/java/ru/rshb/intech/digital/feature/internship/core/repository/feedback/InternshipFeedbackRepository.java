package ru.rshb.intech.digital.feature.internship.core.repository.feedback;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipFeedback;

public interface InternshipFeedbackRepository extends JpaRepository<InternshipFeedback, Long> {

  boolean existsByEmailIgnoreCase(@Nonnull String email);

}
