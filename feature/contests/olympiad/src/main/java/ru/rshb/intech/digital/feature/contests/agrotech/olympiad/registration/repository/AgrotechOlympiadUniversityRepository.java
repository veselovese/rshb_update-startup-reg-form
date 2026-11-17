package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.University;

import java.util.List;
import java.util.Optional;

public interface AgrotechOlympiadUniversityRepository extends JpaRepository<University, Long> {

  @Nonnull
  List<University> findAllByTopIndexIsNotNullAndDisabledIsFalseOrderByTopIndexAsc();

  @Nonnull
  default List<University> findAllTopUniversities() {
    return findAllByTopIndexIsNotNullAndDisabledIsFalseOrderByTopIndexAsc();
  }

  @Query(
      """
      select u
      from AgrotechOlympiadRegUniversity u
      where
        (lower(u.fullTitle) like lower(concat('%', :title, '%'))
          or lower(u.shortTitle) like lower(concat('%', :title, '%')))
        and u.disabled = false
      order by u.shortTitle
      """
  )
  @Nonnull
  List<University> findAllByTitleIgnoreCase(@Nonnull String title, @Nonnull Pageable pageable);
  @Nonnull
  Optional<University> findByExternalIdIgnoreCase(@Nonnull String externalId);
}
