package ru.rshb.intech.digital.feature.internship.core.repository.fields;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipField;

import java.util.List;
import java.util.Set;

public interface InternshipFieldRepository extends JpaRepository<InternshipField, Long> {

  @Query(
      """
      select f
      from InternshipField f
      where f.enabled = true and f.deleted = false
      """
  )
  List<InternshipField> findAllEnabled();

  @Query(
      """
      select f
      from InternshipField f
      where f.deleted = false
      """
  )
  List<InternshipField> findAllNonDeleted();

  @Query(
      """
      select f.title
      from InternshipField f
      where f.title in (:titles)
          and f.enabled = true and f.deleted = false
      """
  )
  List<String> findEnabledWithTitlesIn(@Nonnull Set<String> titles);

  boolean existsAllByTitleIsIn(Set<String> titles);
}
