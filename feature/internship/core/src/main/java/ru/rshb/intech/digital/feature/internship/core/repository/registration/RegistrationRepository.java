package ru.rshb.intech.digital.feature.internship.core.repository.registration;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rshb.intech.digital.feature.internship.core.entity.CountsByMonth;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

  boolean existsRegistrationByPhone(@Nonnull String phone);

  @Query(
      value = """
              select count(r)
              from Registration r
              where r.registrationDate >= :periodStart and r.registrationDate <= :periodEnd
              """
  )
  long findCountInPeriod(@Nonnull LocalDateTime periodStart, @Nonnull LocalDateTime periodEnd);

  @Query(
      value = """
              select count(r)
              from Registration r
              where r.registrationDate >= :periodStart and r.registrationDate <= :periodEnd
              and r.internshipField like concat('%', :field, '%')
              """
  )
  long findCountInPeriod(@Nonnull LocalDateTime periodStart, @Nonnull LocalDateTime periodEnd, @Nonnull String field);

  @Query(
      value = """
              select count(r)
              from Registration r
              where r.internshipField like concat('%', :field, '%')
              """
  )
  long getCountByField(@Nonnull String field);

  @Nonnull
  Optional<Registration> findFirstByOrderByRegistrationDateAsc();

  @Query(
      nativeQuery = true,
      value = """
              select date_trunc('month', r.registration_date) as month, count(r.id) as count
              from digital_internship.o_plt_registration r
              where r.registration_date >= :startDate and r.registration_date <= :endDate
              and r.internship_field like concat('%', :field, '%')
              group by date_trunc('month', r.registration_date)
              order by month
              """
  )
  List<CountsByMonth> findCountPerMonth(@Nonnull LocalDateTime startDate, @Nonnull LocalDateTime endDate, @Nonnull String field);

  @Query(
      nativeQuery = true,
      value = """
              select date_trunc('month', r.registration_date) as month, count(r.id) as count
              from digital_internship.o_plt_registration r
              where r.registration_date >= :startDate and r.registration_date <= :endDate
              group by date_trunc('month', r.registration_date)
              order by month
              """
  )
  List<CountsByMonth> findCountPerMonth(@Nonnull LocalDateTime startDate, @Nonnull LocalDateTime endDate);
}
